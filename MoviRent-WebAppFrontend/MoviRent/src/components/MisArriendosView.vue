¡Entendido! Vamos a agregar la funcionalidad para cancelar un arriendo en el componente `MisArriendos.vue`, asegurándonos de que el vehículo vuelva a estar disponible y que solo se pueda cancelar si la fecha de inicio no es igual a la fecha actual.

### Modificar `MisArriendos.vue`

#### `MisArriendos.vue`

Vamos a agregar un botón para cancelar el arriendo y la lógica necesaria para manejar esta acción.

```vue
<template>
  <div>
    <div class="form-container">
      <h1>Mis Arriendos</h1>

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
          <button @click="mostrarModalExtender(arriendo)">Extender Arriendo</button>
          <button @click="cancelarArriendo(arriendo)" :disabled="!puedeCancelar(arriendo)">Cancelar Arriendo</button>
        </div>
      </div>
    </div>

    <p v-if="arriendos.length === 0" class="mensaje">No se encontraron arriendos.</p>

    <!-- Mensaje de error -->
    <p v-if="error" class="mensaje-error">{{ error }}</p>

    <!-- Modal para extender arriendo -->
    <div v-if="arriendoSeleccionado" class="modal">
      <div class="modal-content">
        <h3>Extender Arriendo</h3>
        <label for="nuevaFechaTermino">Nueva Fecha de Término:</label>
        <input type="date" v-model="nuevaFechaTermino" required :min="getMinFechaTermino()" />
        <button @click="extenderArriendo">Confirmar</button>
        <button @click="cerrarModal">Cancelar</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { ref, reactive } from 'vue';

export default {
  data() {
    return {
      arriendos: [], // Lista de arriendos del usuario actual
      arriendoSeleccionado: null,
      nuevaFechaTermino: '',
      error: "",
    };
  },
  async created() {
    try {
      const usuario = JSON.parse(localStorage.getItem("usuario"));
      if (usuario && usuario.id) {
        const response = await axios.get(`http://localhost:8080/arriendo/mis-arriendos/${usuario.id}`);
        this.arriendos = response.data;
      } else {
        this.error = "No se encontró información del usuario.";
      }
    } catch (error) {
      this.error = "Error al obtener los arriendos.";
      console.error('Error al obtener los arriendos:', error);
    }
  },
  methods: {
    formatDate(fecha) {
      if (!fecha) return "Fecha no disponible";
      const date = new Date(fecha);
      return date.toLocaleDateString();
    },
    mostrarModalExtender(arriendo) {
      this.arriendoSeleccionado = arriendo;
      this.nuevaFechaTermino = '';
    },
    cerrarModal() {
      this.arriendoSeleccionado = null;
      this.nuevaFechaTermino = '';
    },
    getMinFechaTermino() {
      if (!this.arriendoSeleccionado) return "";
      const fechaTerminoActual = new Date(this.arriendoSeleccionado.fechaTermino);
      fechaTerminoActual.setDate(fechaTerminoActual.getDate() + 1); // Sumar 1 día a la fecha de término actual
      return fechaTerminoActual.toISOString().split('T')[0]; // Convertir a formato YYYY-MM-DD
    },
    puedeCancelar(arriendo) {
      const fechaInicio = new Date(arriendo.fechaInicio);
      const fechaActual = new Date();
      return fechaInicio > fechaActual;
    },
    async cancelarArriendo(arriendo) {
      if (!this.puedeCancelar(arriendo)) {
        alert("No puedes cancelar el arriendo si ya ha comenzado.");
        return;
      }

      try {
        await axios.put(`http://localhost:8080/arriendo/cancelar/${arriendo.id}`);
        alert("Arriendo cancelado con éxito.");
        this.obtenerMisArriendos();
      } catch (error) {
        console.error('Error al cancelar el arriendo:', error);
        alert('Hubo un problema al cancelar el arriendo.');
      }
    },
    async extenderArriendo() {
      if (!this.nuevaFechaTermino) {
        alert('Por favor, selecciona una nueva fecha de término.');
        return;
      }

      const fechaInicio = new Date(this.arriendoSeleccionado.fechaInicio);
      const nuevaFechaTerminoDate = new Date(this.nuevaFechaTermino);
      nuevaFechaTerminoDate.setDate(nuevaFechaTerminoDate.getDate() + 1); // Sumar 1 día a la nueva fecha de término
      const diffTime = nuevaFechaTerminoDate - fechaInicio;
      const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
      if (diffDays > 30) {
        alert("No puedes extender el arriendo más allá de 30 días.");
        return;
      }

      try {
        const response = await axios.put(`http://localhost:8080/arriendo/extender/${this.arriendoSeleccionado.id}`, {
          fechaTermino: nuevaFechaTerminoDate.toISOString().split('T')[0] // Convertir a formato YYYY-MM-DD
        });
        alert("Arriendo extendido con éxito.");
        this.cerrarModal();
        this.obtenerMisArriendos();
      } catch (error) {
        console.error('Error al extender el arriendo:', error);
        alert('Hubo un problema al extender el arriendo.');
      }
    },
    async obtenerMisArriendos() {
      const usuario = JSON.parse(localStorage.getItem("usuario"));
      if (usuario && usuario.id) {
        const response = await axios.get(`http://localhost:8080/arriendo/mis-arriendos/${usuario.id}`);
        this.arriendos = response.data;
      } else {
        this.error = "No se encontró información del usuario.";
      }
    }
  }
};
</script>

<style scoped>
h1 {
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

button {
  padding: 5px 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 10px;
}

button:hover {
  background-color: #0056b3;
}

button[disabled] {
  background-color: #cccccc;
  cursor: not-allowed;
}

/* Estilos para el modal */
.modal {
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
}

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 5px;
  text-align: center;
}

.modal-content h3 {
  margin-top: 0;
}

.modal-content input {
  width: 100%;
  padding: 8px;
  margin:  10px 0;
  box-sizing: border-box;
}

.modal-content button {
  padding: 10px 20px;
  margin: 5px;
  cursor: pointer;
}
</style>
