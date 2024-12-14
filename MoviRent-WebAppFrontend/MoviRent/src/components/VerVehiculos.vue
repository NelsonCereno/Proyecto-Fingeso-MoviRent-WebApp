<template>
  <div>
    <h1>Lista de Vehículos</h1>

    <!-- Filtros -->
    <div>
      <label>
        Disponibilidad:
        <select v-model="filtroDisponibilidad" @change="filtrarPorDisponibilidad">
          <option value="">Todos</option>
          <option :value="true">Disponibles</option>
          <option :value="false">No disponibles</option>
        </select>
      </label>

      <label>
        Capacidad:
        <select v-model="filtroCapacidad" @change="filtrarPorCapacidad">
          <option value="">Todas</option>
          <!-- Las capacidades únicas se calculan dinámicamente -->
          <option v-for="capacidad in capacidadesUnicas" :key="capacidad" :value="capacidad">
            {{ capacidad }} Pasajeros
          </option>
        </select>
      </label>
    </div>

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
    <p v-else>No se encontraron vehículos.</p>

    <!-- Mensaje de error -->
    <p v-if="error" style="color: red;">{{ error }}</p>
  </div>
</template>



  
<script>
import axios from 'axios';

export default {
  name: 'VerVehiculos',
  data() {
    return {
      vehiculos: [], // Lista de vehículos
      filtroDisponibilidad: '', // Valor del filtro de disponibilidad
      filtroCapacidad: '', // Valor del filtro de capacidad
      error: '', // Mensaje de error
    };
  },
  computed: {
  capacidadesUnicas() {
    // Validar que vehiculos es un arreglo
    if (!Array.isArray(this.vehiculos)) return [];
    return [...new Set(this.vehiculos
      .map(v => v.nroPasajeros || 0) // Si nroPasajeros es null/undefined, usar 0
      .filter(n => n > 0))] // Excluye valores no válidos
      .sort((a, b) => a - b);
  },
},

  created() {
    this.obtenerTodosLosVehiculos(); // Carga inicial de todos los vehículos
  },
  methods: {
    // Obtiene todos los vehículos desde el backend
    async obtenerTodosLosVehiculos() {
  try {
    const respuesta = await axios.get('/vehiculo/todos');
    console.log('Datos recibidos:', respuesta.data); // Depuración
    this.vehiculos = Array.isArray(respuesta.data) ? respuesta.data : [];
  } catch (error) {
    console.error('Error al obtener vehículos:', error);
    this.error = 'Error al cargar vehículos.';
    this.vehiculos = [];
  }
}

,

    // Filtra vehículos por disponibilidad
    async filtrarPorDisponibilidad() {
      if (this.filtroDisponibilidad === '') {
        // Si el filtro es "Todos", carga todos los vehículos
        this.obtenerTodosLosVehiculos();
        return;
      }
      try {
        const respuesta = await axios.get('/vehiculo/disponibles', {
        params: { disponible: this.filtroDisponibilidad },
        });
        if (respuesta.data.length) {
          this.vehiculos = respuesta.data; // Asigna los vehículos filtrados
          this.error = '';
       } else {
          this.vehiculos = []; // Lista vacía si no hay vehículos disponibles
          this.error = 'No hay vehículos disponibles con este filtro.';
        }
      } catch (error) {
        this.error = 'Error al filtrar por disponibilidad.';
        console.error('Error al filtrar disponibilidad:', error);
      }
    },

    // Filtra vehículos por capacidad
    async filtrarPorCapacidad() {
      if (this.filtroCapacidad === '') {
        this.obtenerTodosLosVehiculos(); // Si no hay filtro, carga todos
        return;
      }
      try {
        const respuesta = await axios.get('/vehiculo/capacidad', {
          params: { nroPasajeros: this.filtroCapacidad }, // Filtro por nroPasajeros
        });
        console.log('Filtrado por capacidad:', respuesta.data);
        this.vehiculos = respuesta.data; // Asigna los datos filtrados
        this.error = ''; // Limpia el mensaje de error
      } catch (error) {
        this.error = 'Error al filtrar por capacidad.';
        console.error('Error al filtrar capacidad:', error);
      }
    },
  },
};
</script>

  
  <style>
  /* Estilos opcionales */
  label {
    margin-right: 10px;
  }
  </style>
  