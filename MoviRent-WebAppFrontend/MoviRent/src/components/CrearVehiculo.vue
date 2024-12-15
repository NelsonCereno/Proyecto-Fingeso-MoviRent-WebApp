<template>
    <div class="form-container">
      <h2>Crear Vehículo</h2>
      <form @submit.prevent="crearVehiculo">
        <div>
          <label for="patente">Patente:</label>
          <input type="text" id="patente" v-model="nuevoVehiculo.patente" required />
        </div>
        <div>
          <label for="nroPasajeros">Número de Pasajeros:</label>
          <input type="number" id="nroPasajeros" v-model="nuevoVehiculo.nroPasajeros" required />
        </div>
        <div>
          <label for="capacidadMaletero">Capacidad de Maletero:</label>
          <input type="number" id="capacidadMaletero" v-model="nuevoVehiculo.capacidadMaletero" required />
        </div>
        <div>
          <label for="codigoAcriss">Código ACRISS:</label>
          <input type="text" id="codigoAcriss" v-model="nuevoVehiculo.codigoAcriss" required />
        </div>
        <div>
          <label for="marca">Marca:</label>
          <input type="text" id="marca" v-model="nuevoVehiculo.marca" required />
        </div>
        <div>
          <label for="modelo">Modelo:</label>
          <input type="text" id="modelo" v-model="nuevoVehiculo.modelo" required />
        </div>
        <div>
          <label for="color">Color:</label>
          <input type="text" id="color" v-model="nuevoVehiculo.color" required />
        </div>
        <div>
          <label for="anio">Año:</label>
          <input type="text" id="anio" v-model="nuevoVehiculo.anio" required />
        </div>
        <div>
          <label for="precio">Precio:</label>
          <input type="text" id="precio" v-model="nuevoVehiculo.precio" required />
        </div>
        <div>
          <label for="fechaRevision">Fecha de Revisión:</label>
          <input type="date" id="fechaRevision" v-model="nuevoVehiculo.fechaRevision" required />
        </div>
        <div>
          <label for="kilometraje">Kilometraje:</label>
          <input type="text" id="kilometraje" v-model="nuevoVehiculo.kilometraje" required />
        </div>
        <div>
          <label for="disponibilidad">Disponibilidad:</label>
          <select id="disponibilidad" v-model="nuevoVehiculo.disponibilidad" required>
            <option :value="true">Disponible</option>
            <option :value="false">No Disponible</option>
          </select>
        </div>
        <div>
          <label for="tipo">Tipo:</label>
          <input type="text" id="tipo" v-model="nuevoVehiculo.tipo" required />
        </div>
        <div>
          <label for="estado">Estado:</label>
          <input type="text" id="estado" v-model="nuevoVehiculo.estado" required />
        </div>
        <div>
          <label for="descripcion">Descripción:</label>
          <textarea id="descripcion" v-model="nuevoVehiculo.descripcion" required></textarea>
        </div>
        <div>
          <label for="imagen">URL de la Imagen:</label>
          <input type="text" id="imagen" v-model="nuevoVehiculo.imagen" required />
        </div>
        <button type="submit">Crear Vehículo</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        nuevoVehiculo: {
          patente: "",
          nroPasajeros: null,
          capacidadMaletero: null,
          codigoAcriss: "",
          marca: "",
          modelo: "",
          color: "",
          anio: "",
          precio: "",
          fechaRevision: "",
          kilometraje: "",
          disponibilidad: true,
          tipo: "",
          estado: "",
          descripcion: "",
          imagen: ""
        },
      };
    },
    methods: {
      async crearVehiculo() {
        try {
          console.log("Datos enviados:", this.nuevoVehiculo);
          const response = await axios.post("http://localhost:8080/vehiculo/crear", this.nuevoVehiculo);
          alert("Vehículo creado con éxito: " + response.data);
          this.limpiarFormulario();
        } catch (error) {
          console.error("Error al crear el vehículo:", error);
          alert("Error al crear el vehículo");
        }
      },
      limpiarFormulario() {
        this.nuevoVehiculo = {
          patente: "",
          nroPasajeros: null,
          capacidadMaletero: null,
          codigoAcriss: "",
          marca: "",
          modelo: "",
          color: "",
          anio: "",
          precio: "",
          fechaRevision: "",
          kilometraje: "",
          disponibilidad: true,
          tipo: "",
          estado: "",
          descripcion: "",
          imagen: ""
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
  