<template>
  <div class="form-container">
    <h2>Crear Reporte</h2>
    <form @submit.prevent="crearReporte">
      <div class="form-group">
        <label for="tipoReporte">Tipo de Reporte:</label>
        <select id="tipoReporte" v-model="nuevoReporte.tipoReporte" required>
          <option value="Reporte falla">Reporte falla</option>
          <option value="Reporte recepcion">Reporte recepción</option>
          <option value="Reporte cambio de sucursal">Reporte cambio de sucursal</option>
        </select>
      </div>
      <div class="form-group">
        <label for="contenido">Contenido:</label>
        <textarea id="contenido" v-model="nuevoReporte.contenido" rows="4" required></textarea>
      </div>
      <div class="form-group">
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
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  background: #f9f9f9;
  border: 1px solid #ccc;
  border-radius: 5px;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

form {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.form-group {
  display: flex;
  flex-direction: column;
}

label {
  font-weight: bold;
  margin-bottom: 5px;
}

select,
textarea,
input {
  width: 100%;
  padding: 8px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  padding: 10px;
  font-size: 16px;
  color: white;
  background-color: #007bff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>
