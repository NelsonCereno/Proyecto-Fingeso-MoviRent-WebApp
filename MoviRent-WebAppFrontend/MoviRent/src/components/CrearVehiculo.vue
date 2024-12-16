<template>
  <div class="form-container">
    <h2>Crear Vehículo</h2>
    <form @submit.prevent="crearVehiculo">
      <div class="form-row">
        <div class="form-field">
          <label for="patente">Patente:</label>
          <input type="text" id="patente" v-model="nuevoVehiculo.patente" required />
        </div>
        <div class="form-field">
          <label for="codigoAcriss">Código ACRISS:</label>
          <input type="text" id="codigoAcriss" v-model="nuevoVehiculo.codigoAcriss" required />
        </div>
      </div>
      <div class="form-row">
        <div class="form-field">
          <label for="marca">Marca:</label>
          <input type="text" id="marca" v-model="nuevoVehiculo.marca" required />
        </div>
        <div class="form-field">
          <label for="modelo">Modelo:</label>
          <input type="text" id="modelo" v-model="nuevoVehiculo.modelo" required />
        </div>
      </div>
      <div class="form-row">
        <div class="form-field">
          <label for="anio">Año:</label>
          <input type="text" id="anio" v-model="nuevoVehiculo.anio" required />
        </div>
        <div class="form-field">
          <label for="color">Color:</label>
          <input type="text" id="color" v-model="nuevoVehiculo.color" required />
        </div>
      </div>
      <div class="form-row">
        <div class="form-field">
          <label for="nroPasajeros">Número de Pasajeros:</label>
          <input type="number" id="nroPasajeros" v-model="nuevoVehiculo.nroPasajeros" required />
        </div>
        <div class="form-field">
          <label for="capacidadMaletero">Capacidad de Maletero:</label>
          <input type="number" id="capacidadMaletero" v-model="nuevoVehiculo.capacidadMaletero" required />
        </div>
      </div>
      <div class="form-row">
        <div class="form-field">
          <label for="precio">Precio:</label>
          <input type="number" id="precio" v-model="nuevoVehiculo.precio" required />
        </div>
        <div class="form-field">
          <label for="disponibilidad">Disponibilidad:</label>
          <select id="disponibilidad" v-model="nuevoVehiculo.disponibilidad" required>
            <option :value="true">Disponible</option>
            <option :value="false">No Disponible</option>
          </select>
        </div>
      </div>
      <div class="form-row">
        <div class="form-field">
          <label for="fechaRevision">Fecha de Revisión:</label>
          <input type="date" id="fechaRevision" v-model="nuevoVehiculo.fechaRevision" required />
        </div>
        <div class="form-field">
          <label for="kilometraje">Kilometraje:</label>
          <input type="number" id="kilometraje" v-model="nuevoVehiculo.kilometraje" required />
        </div>
      </div>
      <div class="form-row">
        <div class="form-field">
          <label for="tipo">Tipo:</label>
          <input type="text" id="tipo" v-model="nuevoVehiculo.tipo" required />
        </div>
        <div class="form-field">
          <label for="estado">Estado:</label>
          <input type="text" id="estado" v-model="nuevoVehiculo.estado" required />
        </div>
      </div>
      <div class="form-row">
        <div class="form-field">
          <label for="descripcion">Descripción:</label>
          <textarea id="descripcion" v-model="nuevoVehiculo.descripcion" required></textarea>
        </div>
        <div class="form-field">
          <label for="imagen">URL de la Imagen:</label>
          <input type="text" id="imagen" v-model="nuevoVehiculo.imagen" required />
        </div>
      </div>
      <button type="submit">Crear Vehículo</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      nuevoVehiculo: {
        patente: "",
        codigoAcriss: "",
        marca: "",
        modelo: "",
        anio: "",
        color: "",
        nroPasajeros: null,
        capacidadMaletero: null,
        precio: "",
        disponibilidad: true,
        fechaRevision: "",
        kilometraje: "",
        tipo: "",
        estado: "",
        descripcion: "",
        imagen: "",
      },
    };
  },
  methods: {
    async crearVehiculo() {
  try {
    console.log("Datos enviados:", this.nuevoVehiculo);
    const response = await axios.post("http://localhost:8080/vehiculo/crear", this.nuevoVehiculo);
    alert("Vehículo creado con éxito.");
    this.limpiarFormulario();
  } catch (error) {
    if (error.response && error.response.status === 400) {
      // Captura el mensaje del servidor y lo muestra al usuario
      alert("Error al crear el vehículo: " + error.response.data);
    } else {
      console.error("Error desconocido al crear el vehículo:", error);
      alert("Error al crear el vehículo. Inténtelo nuevamente.");
    }
  }
    },
    limpiarFormulario() {
      this.nuevoVehiculo = {
        patente: "",
        codigoAcriss: "",
        marca: "",
        modelo: "",
        anio: "",
        color: "",
        nroPasajeros: null,
        capacidadMaletero: null,
        precio: "",
        disponibilidad: true,
        fechaRevision: "",
        kilometraje: "",
        tipo: "",
        estado: "",
        descripcion: "",
        imagen: "",
      };
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
  text-align: left;
}

form {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.form-row {
  display: flex;
  gap: 15px;
  justify-content: space-between;
}

.form-field {
  flex: 1;
  display: flex;
  margin-right: 10px;
}

.form-field:last-child {
  margin-right: 0;
}

label {
  font-weight: bold;
  margin-bottom: 5px;
}

input,
textarea,
select {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  margin-top: 15px;
  padding: 10px;
  background: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background: #0056b3;
}
</style>
