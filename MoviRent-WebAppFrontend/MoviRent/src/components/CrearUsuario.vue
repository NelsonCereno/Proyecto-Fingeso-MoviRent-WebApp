<template>
    <div>
      <h2>Crear Usuario</h2>
      <form @submit.prevent="crearUsuario">
        <div>
          <label for="nombre">Nombre:</label>
          <input type="text" id="nombre" v-model="nuevoUsuario.nombre" required />
        </div>
        <div>
          <label for="apellido">Apellido:</label>
          <input type="text" id="apellido" v-model="nuevoUsuario.apellido" required />
        </div>
        <div>
          <label for="correo">Correo:</label>
          <input type="email" id="correo" v-model="nuevoUsuario.correo" required />
        </div>
        <div>
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
  form {
    display: flex;
    flex-direction: column;
    gap: 10px;
    width: 300px;
  }
  
  button {
    margin-top: 10px;
  }
  </style>
  