<template>
  <div class="form-container">
    <h2>Lista de Reportes</h2>
    <div class="table-container">
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Fecha</th>
            <th>Tipo</th>
            <th>Contenido</th>
            <th>ID de Usuario</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="reporte in reportes" :key="reporte.idReporte">
            <td>{{ reporte.idReporte }}</td>
            <td>{{ reporte.fechaReporte }}</td>
            <td>{{ reporte.tipoReporte }}</td>
            <td>{{ reporte.contenido }}</td>
            <td>{{ reporte.usuarioId }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      reportes: [],
    };
  },
  created() {
    this.obtenerReportes();
  },
  methods: {
    async obtenerReportes() {
      try {
        const response = await axios.get("http://localhost:8080/reportes/todos");
        this.reportes = response.data;
      } catch (error) {
        console.error("Error al obtener los reportes:", error);
      }
    },
  },
};
</script>

<style scoped>
.form-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  background: #f9f9f9;
  border: 1px solid #ccc;
  border-radius: 5px;
  text-align: center;
}

.table-container {
  overflow-x: auto; /* Permite desplazamiento horizontal si la tabla es muy grande */
  margin-top: 20px;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin: 0 auto;
  text-align: left;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
}

th {
  background-color: #f2f2f2;
  font-weight: bold;
}

tbody tr:nth-child(even) {
  background-color: #f9f9f9;
}

tbody tr:hover {
  background-color: #f1f1f1;
}
</style>
