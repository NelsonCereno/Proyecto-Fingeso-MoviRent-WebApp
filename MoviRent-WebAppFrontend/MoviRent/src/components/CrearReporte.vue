<template>
  <div class="form-container">
    <h2>Crear Reporte</h2>
    <form @submit.prevent="crearReporte">
      <div>
        <label for="tipoReporte">Tipo de Reporte:</label>
        <select id="tipoReporte" v-model="nuevoReporte.tipoReporte" required>
          <option value="Reporte falla">Reporte falla</option>
          <option value="Reporte recepcion">Reporte recepción</option>
          <option value="Reporte cambio de sucursal">Reporte cambio de sucursal</option>
        </select>
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
        fechaReporte: new Date().toISOString().split('T')[0], // Fecha actual
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
        fechaReporte: new Date().toISOString().split('T')[0], // Fecha actual
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
  position: absolute;
  top: 30%;
  left: 3%;
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
