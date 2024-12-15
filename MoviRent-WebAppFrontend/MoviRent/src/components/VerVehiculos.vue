<template>
  <div>
    <h1>Lista de Vehículos</h1>

    <!-- Filtros -->
    <div class="filtro">
      <label for="filtroDisponibilidad">Disponibilidad:</label>
      <select id="filtroDisponibilidad" v-model="filtroDisponibilidad" @change="filtrarPorDisponibilidad">
        <option value="">Todos</option>
        <option :value="'true'">Disponibles</option>
        <option :value="'false'">No disponibles</option>
      </select>
    </div>

    <div class="filtro">
      <label for="filtroCapacidad">Capacidad:</label>
      <select id="filtroCapacidad" v-model="filtroCapacidad" @change="filtrarPorCapacidad">
        <option value="">Todos</option>
        <option v-for="capacidad in capacidadesUnicas" :key="capacidad" :value="capacidad">
          {{ capacidad }} Pasajeros
        </option>
      </select>
    </div>

    <!-- Lista de vehículos -->
    <div class="vehiculos-container">
      <div class="vehiculo" v-for="vehiculo in vehiculos" :key="vehiculo.idVehiculo">
        <p><strong>Patente:</strong> {{ vehiculo.patente || "Sin patente" }}</p>
        <p><strong>Modelo:</strong> {{ vehiculo.modelo || "Sin modelo" }}</p>
        <p><strong>Marca:</strong> {{ vehiculo.marca || "Sin marca" }}</p>
        <p><strong>Capacidad:</strong> 
          {{ vehiculo.nroPasajeros > 0 ? vehiculo.nroPasajeros + " pasajeros" : "Sin capacidad" }}
        </p>
        <p><strong>Disponibilidad:</strong> 
          {{ vehiculo.disponibilidad ? "Disponible" : "No disponible" }}
        </p>
        <div v-if="vehiculo.imagen">
          <img :src="vehiculo.imagen" alt="Imagen del vehículo" />
        </div>
        <span v-else>Sin imagen</span>
        <button @click="irACrearArriendo(vehiculo.idVehiculo)">Arrendar</button>
      </div>
    </div>

    <p v-if="vehiculos.length === 0" class="mensaje">No se encontraron vehículos.</p>

    <!-- Mensaje de error -->
    <p v-if="error" class="mensaje-error">{{ error }}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'VerVehiculos',
  data() {
    return {
      vehiculos: [], // Lista de vehículos a mostrar
      filtroDisponibilidad: '', // Valor inicial del filtro ('Todos')
      filtroCapacidad: '', // Valor inicial del filtro de capacidad
      error: '', // Mensaje de error si ocurre algún problema
    };
  },
  computed:  {
    capacidadesUnicas() {
      // Extrae las capacidades únicas y las ordena ascendentemente
      const capacidades = [...new Set(this.vehiculos.map((v) => v.nroPasajeros))];
      return capacidades.sort((a, b) => a - b); // Orden ascendente
    }
  },
  created() {
    this.obtenerTodosLosVehiculos(); // Carga inicial de todos los vehículos
  },
  methods: {
    // Obtiene todos los vehículos desde el backend
    async obtenerTodosLosVehiculos() {
      try {
        console.log('Solicitando todos los vehículos...');
        const respuesta = await axios.get('http://localhost:8080/vehiculo/todos'); // URL completa
        console.log('Respuesta del backend:', respuesta.data); // Depuración
        this.vehiculos = Array.isArray(respuesta.data) ? respuesta.data : [];
      } catch (error) {
        this.error = 'Error al cargar vehículos.';
        console.error('Error en obtenerTodosLosVehiculos:', error);
      }
    },

    // Filtra vehículos por disponibilidad
    async filtrarPorDisponibilidad() {
      try {
        switch (this.filtroDisponibilidad) {
          case '': // Todos
            console.log('Cargando todos los vehículos...');
            this.obtenerTodosLosVehiculos(); // Llama al método para obtener todos
            break;
          case 'true': // Disponibles
            console.log('Cargando vehículos disponibles...');
            const respuestaDisponibles = await axios.get('http://localhost:8080/vehiculo/disponibles?disponibilidad=true');
            console.log('Vehículos disponibles:', respuestaDisponibles.data);
            this.vehiculos = Array.isArray(respuestaDisponibles.data)
              ? respuestaDisponibles.data
              : [];
            break;
          case 'false': // No disponibles
            console.log('Cargando vehículos no disponibles...');
            const respuestaNoDisponibles = await axios.get('http://localhost:8080/vehiculo/disponibles?disponibilidad=false');
            console.log('Vehículos no disponibles:', respuestaNoDisponibles.data);
            this.vehiculos = Array.isArray(respuestaNoDisponibles.data)
              ? respuestaNoDisponibles.data
              : [];
            break;
          default:
            console.log('Opción no reconocida');
            this.error = 'Opción no válida en el filtro de disponibilidad.';
        }
      } catch (error) {
        console.error('Error al filtrar por disponibilidad:', error);
        this.error = 'Error al cargar vehículos según disponibilidad.';
      }
    },

    async filtrarPorCapacidad() {
      if (this.filtroCapacidad === '') {
        this.obtenerTodosLosVehiculos(); // Si no hay filtro, carga todos
        return;
      }
      try {
        // Construcción dinámica de la URL con el parámetro seleccionado
        const url = `http://localhost:8080/vehiculo/capacidad?nroPasajeros=${this.filtroCapacidad}`;
        console.log('URL solicitada:', url);

        // Realiza la solicitud al backend
        const respuesta = await axios.get(url);
        console.log('Respuesta del backend:', respuesta.data);

        // Actualiza la lista de vehículos con los datos filtrados
        this.vehiculos = respuesta.data;

        // Limpia el mensaje de error
        this.error = '';
      } catch (error) {
        // Manejo de errores
        this.error = 'Error al filtrar por capacidad.';
        console.error('Error al filtrar capacidad:', error);
      }
    },
    
    // Método para redirigir a la página de creación de arriendo
    irACrearArriendo(idVehiculo) {
      this.$router.push({ name: 'arriendos', query: { idVehiculo } });
    },
  },
};
</script>

<style>
/* Estilos opcionales */
h1 {
  display: block;
  margin: 0 auto 2rem;
  position: absolute;
  top: 33%;
  left: 3%;
}

.filtro {
  margin-bottom: 20px; /* Añade espacio entre los filtros */
}

.mensaje {
  display: block;
  margin: 0 auto 2rem;
  position: absolute;
  top: 48%;
  left: 3%;
}

.mensaje-error {
  display: block;
  margin: 0 auto 2rem;
  position: absolute;
  top: 53%;
  left: 3%;
  color: brown;
}

.vehiculos-container {
  display: flex;
  flex-wrap: wrap;
  gap: 10px; /* Espacio entre los elementos */
  justify-content: flex-start;
  position: absolute;
  top: 58%;
  left: 3%;
  right: 3%;
}

.vehiculo {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: calc(33.33% - 10px); /* Tres columnas con espacio entre ellas */
  margin-bottom: 20px;
}

.vehiculo img {
  width: 100%;
  height: auto;
  max-width: 300px; /* Ajusta según tus necesidades */
  display: block;
  margin-top: 10px;
}
</style>
