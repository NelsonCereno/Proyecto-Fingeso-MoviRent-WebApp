<template>
  <div>
    <div class="form-container">
      <h2>Lista de Arriendos</h2>

      <!-- Lista de arriendos -->
      <div class="arriendos-container">
        <div class="arriendo" v-for="arriendo in arriendos" :key="arriendo.id">
          <p><strong>ID Arriendo:</strong> {{ arriendo.id }}</p>
          <p><strong>ID Usuario:</strong> {{ arriendo.usuarioId }}</p>
          <p><strong>ID del Vehículo:</strong> {{ arriendo.idVehiculo }}</p>
          <p><strong>Fecha Inicio:</strong> {{ formatDate(arriendo.fechaInicio) }}</p>
          <p><strong>Fecha Término:</strong> {{ formatDate(arriendo.fechaTermino) }}</p>
          <p><strong>Monto a Pagar:</strong> ${{ arriendo.montoPagar }}</p>
          <p><strong>ID Sucursal Llegada:</strong> {{ arriendo.idSucursalLlegada }}</p>
          <p><strong>ID Sucursal Partida:</strong> {{ arriendo.idSucursalPartida }}</p>
          <p><strong>Licencia:</strong></p>
          <div v-if="arriendo.licencia">
            <img :src="arriendo.licencia" alt="Licencia del arriendo" />
          </div>
          <p v-else>No registrada</p>
        </div>
      </div>
    </div>

    <p v-if="arriendos.length === 0" class="mensaje">No se encontraron arriendos.</p>

    <!-- Mensaje de error -->
    <p v-if="error" class="mensaje-error">{{ error }}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      arriendos: [], // Aquí almacenaremos los arriendos
      error: "",
    };
  },
  created() {
    this.obtenerArriendos();
  },
  methods: {
    async obtenerArriendos() {
      try {
        const response = await axios.get("http://localhost:8080/arriendo/todos");
        this.arriendos = response.data; // Asigna los datos al arreglo
      } catch (error) {
        this.error = "Error al cargar arriendos.";
        console.error("Error al obtener los arriendos:", error);
      }
    },
    formatDate(fecha) {
      if (!fecha) return "Fecha no disponible";
      const date = new Date(fecha);
      return date.toLocaleDateString();
    },
  },
};
</script>

<style scoped>
h2 {
  text-align: center;
  margin-bottom: 20px;
}

.arriendos-container {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  justify-content: center;
  overflow-y: auto;
  max-height: 400px;
  padding: 10px;
  border: 1px solid #ddd;
  background-color: #f9f9f9;
}

.form-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  background: #f9f9f9;
  border: 1px solid #ccc;
  border-radius: 5px;
  text-align: left;
}

.arriendo {
  display: flex;
  flex-direction: column;
  align-items: center;
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 15px;
  width: 250px;
}

.arriendo img {
  max-width: 100%; /* Se asegura de que la imagen no sobresalga del contenedor */
  height: auto;
}

.mensaje {
  text-align: center;
  color: #888;
  margin-top: 20px;
}

.mensaje-error {
  text-align: center;
  color: red;
  margin-top: 20px;
}
</style>
