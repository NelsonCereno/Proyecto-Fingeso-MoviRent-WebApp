<template>
  <div class="form-container">
    <h2>Crear Usuario</h2>
    <form @submit.prevent="crearUsuario">
      <div class="form-group">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" v-model="nuevoUsuario.nombre" required />
      </div>
      <div class="form-group">
        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" v-model="nuevoUsuario.apellido" required />
      </div>
      <div class="form-group">
        <label for="correo">Correo:</label>
        <input type="email" id="correo" v-model="nuevoUsuario.correo" required />
      </div>
      <div class="form-group">
        <label for="contrasena">Contraseña:</label>
        <input type="password" id="contrasena" v-model="nuevoUsuario.contrasena" required />
      </div>
      <button type="submit">Crear Usuario</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      nuevoUsuario: {
        nombre: "",
        apellido: "",
        correo: "",
        contrasena: "",
      },
    };
  },
  methods: {
    async crearUsuario() {
      try {
        console.log("Datos enviados:", this.nuevoUsuario); // Log de los datos enviados
        const response = await axios.post("http://localhost:8080/usuario/crear", this.nuevoUsuario);
        alert("Usuario creado con éxito: " + response.data);
        this.limpiarFormulario();
      } catch (error) {
        console.error("Error al crear el usuario:", error);
        alert("Error al crear el usuario");
      }
    },
    limpiarFormulario() {
      this.nuevoUsuario = {
        nombre: "",
        apellido: "",
        correo: "",
        contrasena: "",
      };
    },
  },
};
</script>

<style scoped>
.form-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 0 auto;
  padding: 20px;
  max-width: 400px;
  background-color: #f9f9f9;
  border: 1px solid #ccc;
  border-radius: 5px;
}

h2 {
  margin-bottom: 20px;
}

form {
  width: 100%;
}

.form-group {
  display: flex;
  flex-direction: column;
  margin-bottom: 15px;
}

label {
  font-weight: bold;
  margin-bottom: 5px;
}

input {
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
  width: 100%;
}

button:hover {
  background-color: #0056b3;
}
</style>
