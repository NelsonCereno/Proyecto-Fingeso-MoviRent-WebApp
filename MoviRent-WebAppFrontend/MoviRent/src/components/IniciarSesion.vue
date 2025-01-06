<template>
    <div class="form-container">
      <h2>Iniciar Sesión</h2>
      <form @submit.prevent="iniciarSesion">
        <div class="form-group">
          <label for="correo">Correo:</label>
          <input type="email" id="correo" v-model="usuarioLogin.correo" required />
        </div>
        <div class="form-group">
          <label for="contrasena">Contraseña:</label>
          <input type="password" id="contrasena" v-model="usuarioLogin.contrasena" required />
        </div>
        <button type="submit">Iniciar Sesión</button>
      </form>
  
      <!-- Botón para redirigir al registro -->
      <button @click="redirigirRegistro" class="boton-registrar">Registrarse</button>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    data() {
      return {
        usuarioLogin: {
          correo: "",
          contrasena: "",
        },
      };
    },
    methods: {
      async iniciarSesion() {
    try {
        const response = await axios.post("http://localhost:8080/usuario/iniciarSesion", null, {
            params: {
                correo: this.usuarioLogin.correo,
                contrasena: this.usuarioLogin.contrasena,
            },
        });

        console.log("Respuesta del backend:", response.data); // Verificar qué devuelve el backend

        if (response.data) {
            const usuario = {
                id: response.data.id, // Asegúrate de que el backend devuelve este campo
                role: response.data.role,
                correo: this.usuarioLogin.correo,
            };

            if (!usuario.id) {
                alert("Error al procesar el inicio de sesión. Falta la ID del usuario.");
                return;
            }

            alert("Sesión iniciada correctamente.");
            localStorage.setItem("usuario", JSON.stringify(usuario));
            location.reload();
            window.location.href = "/";
        }
    } catch (error) {
        console.error("Error al iniciar sesión:", error);
        alert("Error al iniciar sesión. Verifica tus credenciales.");
    }
},

    redirigirRegistro() {
      this.$router.push("/crearUsuario"); // Redirigir a la ruta de creación de usuario
      },
}
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
    margin-top: 10px;
  }
  
  button:hover {
    background-color: #0056b3;
  }
  
  .boton-registrar {
    background-color: #28a745;
    margin-top: 10px;
  }
  
  .boton-registrar:hover {
    background-color: #218838;
  }
  </style>
  