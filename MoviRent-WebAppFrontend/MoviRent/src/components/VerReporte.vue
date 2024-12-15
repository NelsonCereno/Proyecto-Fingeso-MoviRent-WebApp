<template>
    <div class="form-container">
      <h2>Lista de Reportes</h2>
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
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        reportes: []
      };
    },
    created() {
      this.obtenerReportes();
    },
    methods: {
      async obtenerReportes() {
        try {
          const response = await axios.get('http://localhost:8080/reportes/todos');
          this.reportes = response.data;
        } catch (error) {
          console.error('Error al obtener los reportes:', error);
        }
      }
    }
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
  
  table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
  }
  th, td {
    border: 1px solid #ddd;
    padding: 8px;
  }
  th {
    background-color: #f2f2f2;
  }
  </style>
  