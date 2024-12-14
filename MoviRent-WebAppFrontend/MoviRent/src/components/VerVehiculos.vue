<template>
  <div>
    <h1>Lista de Vehículos</h1>

    <!-- Filtros -->
      <label1>
    Disponibilidad:
    <select v-model="filtroDisponibilidad" @change="filtrarPorDisponibilidad">
      <option value="">Todos</option>
      <option :value="'true'">Disponibles</option>
      <option :value="'false'">No disponibles</option>
    </select>
      </label1>

      <label2>
        Capacidad:
        <select v-model="filtroCapacidad" @change="filtrarPorCapacidad">
          <!-- Las capacidades únicas se calculan dinámicamente -->
          <option v-for="capacidad in capacidadesUnicas" :key="capacidad" :value="capacidad">
            {{ capacidad }} Pasajeros
          </option>
        </select>
      </label2>


    <!-- Lista de vehículos -->
    <ul v-if="vehiculos.length">
      <li v-for="vehiculo in vehiculos" :key="vehiculo.idVehiculo">
        <p><strong>Patente:</strong> {{ vehiculo.patente || "Sin patente" }}</p>
        <p><strong>Modelo:</strong> {{ vehiculo.modelo || "Sin modelo" }}</p>
        <p><strong>Marca:</strong> {{ vehiculo.marca || "Sin marca" }}</p>
        <p><strong>Capacidad:</strong> 
          {{ vehiculo.nroPasajeros > 0 ? vehiculo.nroPasajeros + " pasajeros" : "Sin capacidad" }}
        </p>
        <p><strong>Disponibilidad:</strong> 
          {{ vehiculo.disponibilidad ? "Disponible" : "No disponible" }}
        </p>
        <p>
          <a :href="vehiculo.imagen" target="_blank" v-if="vehiculo.imagen">Ver imagen</a>
          <span v-else>Sin imagen</span>
        </p>
      </li>
    </ul>

    <p1 v-else>No se encontraron vehículos.</p1>

    <!-- Mensaje de error -->
    <p2 v-if="error" style="color: red;">{{ error }}</p2>

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
}


,

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
}
,

async filtrarPorCapacidad() {
  if (this.filtroCapacidad === '') {
    this.obtenerTodosLosVehiculos(); // Si no hay filtro, carga todos
    return;
  }
  try {
    // Construcción dinámica de la URL con el parámetro seleccionado
    const url = `/vehiculo/capacidad?nroPasajeros=${this.filtroCapacidad}`;
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

  },
};
</script>

  
<style>
/* Estilos opcionales */
h1 {
  display: block;
  margin: 0 auto 2rem;
  position: absolute; /* Cambia la posición del logo */
  top: 33%; /* Ajusta la posición desde la parte superior */
  left: 3%; /* Ajusta la posición desde la izquierda */
}

label1 {
  display: block;
  margin: 0 auto 2rem;
  position: absolute; /* Cambia la posición del logo */
  top: 43%; /* Ajusta la posición desde la parte superior */
  left: 3%; /* Ajusta la posición desde la izquierda */
}

label2 {
  display: block;
  margin: 0 auto 2rem;
  position: absolute; /* Cambia la posición del logo */
  top: 43%; /* Ajusta la posición desde la parte superior */
  left: 21%; /* Ajusta la posición desde la izquierda */
}

p1{
  display: block;
  margin: 0 auto 2rem;
  position: absolute; /* Cambia la posición del logo */
  top: 48%; /* Ajusta la posición desde la parte superior */
  left: 3%; /* Ajusta la posición desde la izquierda */
}

p2{
  display: block;
  margin: 0 auto 2rem;
  position: absolute; /* Cambia la posición del logo */
  top: 53%; /* Ajusta la posición desde la parte superior */
  left: 3%; /* Ajusta la posición desde la izquierda */
}

ul{
  display: block;
  margin: 0 auto 2rem;
  position: absolute; /* Cambia la posición del logo */
  top: 58%; /* Ajusta la posición desde la parte superior */
  left: 3%; /* Ajusta la posición desde la izquierda */
}
</style>
  