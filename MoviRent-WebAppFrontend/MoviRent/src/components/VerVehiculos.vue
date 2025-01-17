<template>
  <div>
    <div class="form-container">
      <h1>Lista de Vehículos</h1>

      <!-- Filtros -->
      <div class="filtro">
        <label for="filtroDisponibilidad">Disponibilidad:</label>
        <select id="filtroDisponibilidad" v-model="filtroDisponibilidad" @change="filtrarPorDisponibilidad">
          <option value="">Todos</option>
          <option :value="'true'">Disponibles</option>
          <option :value="'false'">No disponibles</option>
        </select>
      </div>

      <div class="filtro">
        <label for="filtroCapacidad">Capacidad:</label>
        <select id="filtroCapacidad" v-model="filtroCapacidad" @change="filtrarPorCapacidad">
          <option value="">Todos</option>
          <option v-for="capacidad in capacidadesUnicas" :key="capacidad" :value="capacidad">
            {{ capacidad }} Pasajeros
          </option>
        </select>
      </div>

      <!-- Lista de vehículos -->
      <div class="vehiculos-container">
        <div class="vehiculo" v-for="vehiculo in vehiculos" :key="vehiculo.idVehiculo">
          <p><strong>ID del vehiculo:</strong> {{ vehiculo.idVehiculo || "Sin Id" }}</p>
          <p><strong>Patente:</strong> {{ vehiculo.patente || "Sin patente" }}</p>
          <p><strong>Modelo:</strong> {{ vehiculo.modelo || "Sin modelo" }}</p>
          <p><strong>Marca:</strong> {{ vehiculo.marca || "Sin marca" }}</p>
          <p><strong>Capacidad:</strong>
            {{ vehiculo.nroPasajeros > 0 ? vehiculo.nroPasajeros + " pasajeros" : "Sin capacidad" }}
          </p>
          <p><strong>Disponibilidad:</strong>
            {{ vehiculo.disponibilidad ? "Disponible" : "No disponible" }}
          </p>
          <div v-if="vehiculo.imagen">
            <img :src="vehiculo.imagen" alt="Imagen del vehículo" />
          </div>
          <span v-else>Sin imagen</span>

          <!-- Botones -->
          <div class="botones-container">
            <button v-if="usuario?.role !== 'INVITADO'" @click="irACrearArriendo(vehiculo)">Arrendar</button>
            <button v-if="usuario?.role === 'ADMINISTRADOR'" class="btn-editar" @click="irAEditarVehiculo(vehiculo.idVehiculo)">Editar Vehículo</button>
            <button v-if="usuario?.role === 'ADMINISTRADOR'" class="eliminar" @click="cambiarDisponibilidad(vehiculo.idVehiculo)">Eliminar</button>
          </div>
        </div>
      </div>
    </div>

    <p v-if="vehiculos.length === 0" class="mensaje">No se encontraron vehículos.</p>

    <!-- Mensaje de error -->
    <p v-if="error" class="mensaje-error">{{ error }}</p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "VerVehiculos",
  data() {
    return {
      vehiculos: [],
      filtroDisponibilidad: "",
      filtroCapacidad: "",
      error: "",
      usuario: JSON.parse(localStorage.getItem("usuario")) || { role: "INVITADO" },
    };
  },
  computed: {
    capacidadesUnicas() {
      const capacidades = [...new Set(this.vehiculos.map((v) => v.nroPasajeros))];
      return capacidades.sort((a, b) => a - b);
    },
  },
  created() {
    this.obtenerTodosLosVehiculos();
  },
  methods: {
    async obtenerTodosLosVehiculos() {
      try {
        const respuesta = await axios.get("http://localhost:8080/vehiculo/todos");
        this.vehiculos = Array.isArray(respuesta.data) ? respuesta.data : [];

        this.vehiculos.sort((a, b) => {
          if (a.disponibilidad === b.disponibilidad) {
            return a.idVehiculo - b.idVehiculo;
          }
          return a.disponibilidad ? -1 : 1;
        });
      } catch (error) {
        this.error = "Error al cargar vehículos.";
        console.error("Error en obtenerTodosLosVehiculos:", error);
      }
    },
    irACrearArriendo(vehiculo) {
      const usuario = JSON.parse(localStorage.getItem("usuario")); 
      const usuarioId = usuario ? usuario?.id : 0;
      this.$router.push({
        name: "CrearArriendo",
        query: {
          idVehiculo: vehiculo.idVehiculo,
          patente: vehiculo.patente,
          modelo: vehiculo.modelo,
          marca: vehiculo.marca,
          precio: vehiculo.precio,
          disponibilidad: vehiculo.disponibilidad,
          idUsuario: usuario?.id// Añadimos el ID del usuario aquí },
        },
      });
    },
    irAEditarVehiculo(idVehiculo) {
      this.$router.push({ name: "editarVehiculo", params: { idVehiculo } });
    },
    async cambiarDisponibilidad(idVehiculo) {
      const confirmar = window.confirm("¿Estás seguro de que deseas cambiar la disponibilidad de este vehículo?");
      if (!confirmar) return;

      try {
        const url = `http://localhost:8080/vehiculo/actualizar/${idVehiculo}`;
        await axios.put(url, { disponibilidad: false });
        alert("Vehículo actualizado correctamente.");
        this.obtenerTodosLosVehiculos();
      } catch (error) {
        console.error("Error al cambiar la disponibilidad del vehículo:", error);
        alert("Hubo un problema al actualizar el vehículo.");
      }
    },
    async filtrarPorDisponibilidad() {
      if (this.filtroDisponibilidad === "") {
        this.obtenerTodosLosVehiculos();
        return;
      }

      try {
        const url = `http://localhost:8080/vehiculo/disponibles?disponibilidad=${this.filtroDisponibilidad}`;
        const respuesta = await axios.get(url);
        this.vehiculos = Array.isArray(respuesta.data) ? respuesta.data : [];
      } catch (error) {
        console.error("Error al filtrar por disponibilidad:", error);
        this.error = "Error al cargar vehículos según disponibilidad.";
      }
    },
    async filtrarPorCapacidad() {
      if (this.filtroCapacidad === "") {
        this.obtenerTodosLosVehiculos();
        return;
      }

      try {
        const url = `http://localhost:8080/vehiculo/capacidad?nroPasajeros=${this.filtroCapacidad}`;
        const respuesta = await axios.get(url);
        this.vehiculos = Array.isArray(respuesta.data) ? respuesta.data : [];
      } catch (error) {
        console.error("Error al filtrar por capacidad:", error);
        this.error = "Error al filtrar por capacidad.";
      }
    },
  },
};
</script>

<style scoped>
h1 {
  text-align: center;
  margin-bottom: 20px;
}

.filtro {
  margin-bottom: 20px;
}

.vehiculos-container {
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

.vehiculo {
  display: flex;
  flex-direction: column;
  align-items: center;
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 15px;
  width: 250px;
}

.vehiculo img {
  width: 100%;
  height: auto;
}

.botones-container {
  display: flex;
  gap: 10px;
  justify-content: center;
  margin-top: 10px;
}

button.eliminar {
  border: 1px solid red;
  color: red;
  padding: 5px 10px;
  cursor: pointer;
}

button.eliminar:hover {
  background-color: red;
  color: white;
}
</style>