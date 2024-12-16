<template>
  <div class="form-container">
    <h2>Editar Vehículo</h2>
    <form @submit.prevent="actualizarVehiculo">
      <div class="form-row">
        <div class="form-field">
          <label for="patente">Patente:</label>
          <input type="text" id="patente" v-model="vehiculo.patente" required />
        </div>
        <div class="form-field">
          <label for="codigoAcriss">Código ACRISS:</label>
          <input type="text" id="codigoAcriss" v-model="vehiculo.codigoAcriss" required />
        </div>
      </div>
      <div class="form-row">
        <div class="form-field">
          <label for="marca">Marca:</label>
          <input type="text" id="marca" v-model="vehiculo.marca" required />
        </div>
        <div class="form-field">
          <label for="modelo">Modelo:</label>
          <input type="text" id="modelo" v-model="vehiculo.modelo" required />
        </div>
      </div>
      <div class="form-row">
        <div class="form-field">
          <label for="anio">Año:</label>
          <input type="text" id="anio" v-model="vehiculo.anio" required />
        </div>
        <div class="form-field">
          <label for="color">Color:</label>
          <input type="text" id="color" v-model="vehiculo.color" required />
        </div>
      </div>
      <div class="form-row">
        <div class="form-field">
          <label for="nroPasajeros">Número de Pasajeros:</label>
          <input type="number" id="nroPasajeros" v-model="vehiculo.nroPasajeros" required />
        </div>
        <div class="form-field">
          <label for="capacidadMaletero">Capacidad de Maletero:</label>
          <input type="number" id="capacidadMaletero" v-model="vehiculo.capacidadMaletero" required />
        </div>
      </div>
      <div class="form-row">
        <div class="form-field">
          <label for="precio">Precio:</label>
          <input type="text" id="precio" v-model="vehiculo.precio" required />
        </div>
        <div class="form-field">
          <label for="disponibilidad">Disponibilidad:</label>
          <select id="disponibilidad" v-model="vehiculo.disponibilidad" required>
            <option :value="true">Disponible</option>
            <option :value="false">No Disponible</option>
          </select>
        </div>
      </div>
      <div class="form-row">
        <div class="form-field">
          <label for="fechaRevision">Fecha de Revisión:</label>
          <input type="date" id="fechaRevision" v-model="vehiculo.fechaRevision" required />
        </div>
        <div class="form-field">
          <label for="kilometraje">Kilometraje:</label>
          <input type="text" id="kilometraje" v-model="vehiculo.kilometraje" required />
        </div>
      </div>
      <div class="form-row">
        <div class="form-field">
          <label for="tipo">Tipo:</label>
          <input type="text" id="tipo" v-model="vehiculo.tipo" required />
        </div>
        <div class="form-field">
          <label for="estado">Estado:</label>
          <input type="text" id="estado" v-model="vehiculo.estado" required />
        </div>
      </div>
      <div class="form-row">
        <div class="form-field">
          <label for="descripcion">Descripción:</label>
          <textarea id="descripcion" v-model="vehiculo.descripcion" required></textarea>
        </div>
        <div class="form-field">
          <label for="imagen">URL de la Imagen:</label>
          <input type="text" id="imagen" v-model="vehiculo.imagen" required />
        </div>
      </div>
      <button type="submit">Actualizar Vehículo</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";
import { onMounted, reactive } from "vue";
import { useRoute } from "vue-router";

export default {
  name: "EditarVehiculo",
  setup() {
    const route = useRoute();
    const vehiculo = reactive({
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
    });

    onMounted(async () => {
      const idVehiculo = route.params.idVehiculo;
      if (idVehiculo) {
        try {
          const response = await axios.get(`http://localhost:8080/vehiculo/${idVehiculo}`);
          Object.assign(vehiculo, response.data); // Carga los datos del vehículo en el objeto reactivo
        } catch (error) {
          console.error("Error al cargar los datos del vehículo:", error);
          alert("Error al cargar los datos del vehículo.");
        }
      } else {
        alert("ID del vehículo no especificado.");
      }
    });

    const actualizarVehiculo = async () => {
      try {
        const response = await axios.put(
          `http://localhost:8080/vehiculo/actualizarvehiculo/${route.params.idVehiculo}`,
          vehiculo
        );
        alert("Vehículo actualizado con éxito.");
      } catch (error) {
        console.error("Error al actualizar el vehículo:", error);
        alert("Error al actualizar el vehículo.");
      }
    };

    return {
      vehiculo,
      actualizarVehiculo,
    };
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
}

form {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.form-row {
  display: flex;
  gap: 15px;
}

.form-field {
  flex: 1;
  display: flex;
  flex-direction: column;
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
