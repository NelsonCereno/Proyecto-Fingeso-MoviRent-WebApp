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
                console.log("Datos enviados:", this.usuarioLogin); // Ver los datos enviados

                // Realizar la solicitud de inicio de sesión con los parámetros en la URL
                const response = await axios.post("http://localhost:8080/usuario/iniciarSesion", null, {
                    params: {
                        correo: this.usuarioLogin.correo,
                        contrasena: this.usuarioLogin.contrasena,
                    },
                });

                // Verifica si la respuesta contiene datos (puede ser un token o algo similar)
                if (response.data) {
                    alert("Sesión iniciada correctamente.");

                    // Redirigir a la vista "Ver Vehículos"
                    this.$router.push('/vehiculos');
                }
            } catch (error) {
                console.error("Error al iniciar sesión:", error);

                // Verifica el tipo de error
                if (error.response) {
                    // El servidor respondió con un código de estado diferente a 2xx
                    if (error.response.status === 404) {
                        alert("El endpoint de inicio de sesión no existe. Revisa la URL del backend.");
                    } else if (error.response.status === 400) {
                        alert("Datos incorrectos. Revisa tu correo o contraseña.");
                    } else {
                        alert(`Error al iniciar sesión: ${error.response.statusText}`);
                    }
                } else if (error.request) {
                    // La solicitud fue realizada pero no se recibió respuesta
                    alert("No se recibió respuesta del servidor. Verifica la conexión.");
                } else {
                    // Algo ocurrió al configurar la solicitud
                    alert("Error en la configuración de la solicitud: " + error.message);
                }
            }
        }


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