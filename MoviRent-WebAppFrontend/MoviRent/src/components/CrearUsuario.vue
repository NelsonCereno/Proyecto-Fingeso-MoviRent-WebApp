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
      <div>
        <label for="fechaNacimiento">Fecha de Nacimiento:</label>
        <input type="text" id="fechaNacimiento" v-model="nuevoUsuario.fechaNacimiento" required
          @input="formatFechaNacimiento" placeholder="DD/MM/YYYY" />
      </div>
      <div class="form-group">
        <label for="correo">Correo:</label>
        <input type="email" id="correo" v-model="nuevoUsuario.correo" required />
      </div>
      <div>
        <label for="celular">Celular:</label>
        <input type="text" id="celular" v-model="nuevoUsuario.celular" placeholder="+569XXXXXXXX" required />
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
        fechaNacimiento: "",
        correo: "",
        celular: "",
        contrasena: "",
      },
    };
  },
  methods: {
    formatFechaNacimiento(event) {
      let fecha = event.target.value.replace(/\D/g, ""); // Elimina cualquier carácter no numérico
      if (fecha.length <= 2) {
        fecha = fecha.replace(/(\d{2})(\d{0,1})/, "$1/$2");
      } else if (fecha.length <= 4) {
        fecha = fecha.replace(/(\d{2})(\d{2})(\d{0,1})/, "$1/$2/$3");
      } else {
        fecha = fecha.replace(/(\d{2})(\d{2})(\d{4})/, "$1/$2/$3");
      }
      this.nuevoUsuario.fechaNacimiento = fecha; // Actualiza el modelo con el valor formateado
    },
    formatearCelular(event) {
      // Obtener los primeros 8 caracteres del número (descontando el +569 fijo)
      let celular = event.target.value.replace(/\D/g, ""); // Elimina cualquier carácter no numérico
      if (celular.length > 8) {
        celular = celular.substring(0, 8); // Limita a 8 dígitos
      }
      this.nuevoUsuario.celular = `+569${celular}`; // Prepend +569 al celular
    },
    async crearUsuario() {
      try {
        console.log("Datos enviados:", this.nuevoUsuario); // Log de los datos enviados

        // Validar la fecha de nacimiento (formato DD/MM/YYYY)
        const fechaNacimientoRegex = /^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/\d{4}$/;
        if (!fechaNacimientoRegex.test(this.nuevoUsuario.fechaNacimiento)) {
          alert("La fecha de nacimiento debe tener el formato DD/MM/YYYY.");
          return;
        }

        // Validar el celular (formato +569XXXXXXXX)
        const celularRegex = /^\+569\d{8}$/;
        if (!celularRegex.test(this.nuevoUsuario.celular)) {
          alert("El número de celular debe tener el formato +569XXXXXXXX.");
          return;
        }
        // Realizar la solicitud para crear el usuario
        const response = await axios.post("http://localhost:8080/usuario/crear", this.nuevoUsuario);

        // Si la respuesta contiene el mensaje de correo duplicado
        if (response.data.includes("El correo ya está registrado")) {
          alert(response.data);
        } else {
          alert("Usuario creado con éxito.");
          // Redirigir a la vista "Ver Vehículos"
          this.$router.push('/iniciarSesion');
          this.limpiarFormulario();
        }
      } catch (error) {
        console.error("Error al crear el usuario:", error);
        alert("Error al crear el usuario: " + error.message);
      }
    },
    limpiarFormulario() {
      this.nuevoUsuario = {
        nombre: "",
        apellido: "",
        fechaNacimiento: "",
        correo: "",
        celular: "",
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
