<template>
  <div class="form-container">
    <h2>Crear Reporte</h2>
    <form @submit.prevent="crearReporte">
      <div>
        <label for="fechaReporte">Fecha del Reporte:</label>
        <input type="date" id="fechaReporte" v-model="nuevoReporte.fechaReporte" required />
      </div>
      <div>
        <label for="tipoReporte">Tipo de Reporte:</label>
        <input type="text" id="tipoReporte" v-model="nuevoReporte.tipoReporte" required />
      </div>
      <div>
        <label for="contenido">Contenido:</label>
        <textarea id="contenido" v-model="nuevoReporte.contenido" required></textarea>
      </div>
      <div>
        <label for="usuarioId">ID del Usuario:</label>
        <input type="number" id="usuarioId" v-model="nuevoReporte.usuarioId" required />
      </div>
      <button type="submit">Crear Reporte</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      nuevoReporte: {
        fechaReporte: "",
        tipoReporte: "",
        contenido: "",
        usuarioId: null,
      },
    };
  },
  methods: {
    async crearReporte() {
      try {
        console.log("Datos enviados:", this.nuevoReporte);
        const response = await axios.post("http://localhost:8080/reportes/crear", this.nuevoReporte);
        alert("Reporte creado con éxito: " + response.data);
        this.limpiarFormulario();
      } catch (error) {
        console.error("Error al crear el reporte:", error);
        alert("Error al crear el reporte");
      }
    },
    limpiarFormulario() {
      this.nuevoReporte = {
        fechaReporte: "",
        tipoReporte: "",
        contenido: "",
        usuarioId: null,
      };
    },
  },
};
</script>

<style scoped>
.form-container {
  display: absolute;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  position: absolute; /* Cambia la posición del contenedor */
  top: 30%; /* Ajusta la posición desde la parte superior */
  left: 3%; /* Ajusta la posición desde la izquierda */
  
}

form {
  display: flex;
  flex-direction: column;
  gap: 10px;
  width: 300px;
}

button {
  margin-top: 15px;
}
</style>
