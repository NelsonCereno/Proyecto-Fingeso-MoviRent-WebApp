<template>
    <div>
        <h2>Crear Arriendo</h2>
        <form @submit.prevent="crearArriendo">
            <div>
                <label for="fechaInicio">Fecha de Inicio:</label>
                <input type="date" id="fechaInicio" v-model="nuevoArriendo.fechaInicio" required />
            </div>
            <div>
                <label for="fechaTermino">Fecha de Término:</label>
                <input type="date" id="fechaTermino" v-model="nuevoArriendo.fechaTermino" required />
            </div>
            <div>
                <label for="idSucursalLlegada">ID Sucursal de Llegada:</label>
                <input type="number" id="idSucursalLlegada" v-model="nuevoArriendo.idSucursalLlegada" required />
            </div>
            <div>
                <label for="idSucursalPartida">ID Sucursal de Partida:</label>
                <input type="number" id="idSucursalPartida" v-model="nuevoArriendo.idSucursalPartida" required />
            </div>
            <div>
                <label for="idVehiculo">ID Vehículo:</label>
                <input type="number" id="idVehiculo" v-model="nuevoArriendo.idVehiculo" required />
            </div>
            <div>
                <label for="montoPagar">Monto a Pagar:</label>
                <input type="number" id="montoPagar" v-model="nuevoArriendo.montoPagar" required />
            </div>
            <button type="submit">Crear Arriendo</button>
        </form>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            nuevoArriendo: {
                fechaInicio: "",
                fechaTermino: "",
                idSucursalLlegada: "",
                idSucursalPartida: "",
                idVehiculo: "",
                montoPagar: "",
            },
        };
    },
    methods: {
        async crearArriendo() {
            try {
                console.log("Datos enviados:", this.nuevoArriendo); // Log de los datos enviados
                if(await this.comprobarDatos()) { // Comprueba los datos antes de enviar
                    console.log("Datos válidos, enviando datos..."); // Log de datos válidos
                } else {
                    console.log("Datos inválidos, no se enviarán..."); // Log de datos inválidos
                    return;
                } 
                const response = await axios.post("http://localhost:8080/arriendo/crear", this.nuevoArriendo);
                alert("Arriendo creado con éxito: " + response.data);
                this.limpiarFormulario();
            } catch (error) {
                console.error("Error al crear el arriendo:", error);
                alert("Error al crear el arriendo");
            }
        },
        limpiarFormulario() {
            this.nuevoArriendo = {
                fechaInicio: "",
                fechaTermino: "",
                idSucursalLlegada: "",
                idSucursalPartida: "",
                idVehiculo: "",
                montoPagar: "",
            };
        },
        async comprobarDatos() {
            // Validación de los datos ingresados
            if (
                this.nuevoArriendo.fechaInicio === "" ||
                this.nuevoArriendo.fechaTermino === "" ||
                this.nuevoArriendo.idSucursalLlegada === "" ||
                this.nuevoArriendo.idSucursalPartida === "" ||
                this.nuevoArriendo.idVehiculo === "" ||
                this.nuevoArriendo.montoPagar === ""
            ) {
                alert("Por favor, complete todos los campos.");
                return false;
            }
            try {
                console.log("Validando datos...");
                console.log("Validando fecha de inicio...");
                const respuestaFechaInicio = await axios.get(
                    `http://localhost:8080/arriendo/validarFecha?fecha=${this.nuevoArriendo.fechaInicio}`
                );
                console.log("Respuesta del backend:", respuestaFechaInicio.data);
                this.nuevoArriendo.fechaInicio = !!respuestaFechaInicio.data;
                console.log("Validando fecha de término...");
                const respuestaFechaTermino = await axios.get(
                    `http://localhost:8080/arriendo/validarFecha?fecha=${this.nuevoArriendo.fechaTermino}`
                );
                console.log("Respuesta del backend:", respuestaFechaTermino.data);
                this.nuevoArriendo.fechaTermino = !!respuestaFechaTermino.data;
                console.log("Validando ID de sucursal de llegada...");
                const respuestaIdSucursalLlegada = await axios.get(
                    `http://localhost:8080/arriendo/validarSucursal?id=${this.nuevoArriendo.idSucursalLlegada}`
                );
                console.log("Respuesta del backend:", respuestaIdSucursalLlegada.data);
                this.nuevoArriendo.idSucursalLlegada = !!respuestaIdSucursalLlegada.data;
                console.log("Validando ID de sucursal de partida...");
                const respuestaIdSucursalPartida = await axios.get(
                    `http://localhost:8080/arriendo/validarSucursal?id=${this.nuevoArriendo.idSucursalPartida}`
                );
                console.log("Respuesta del backend:", respuestaIdSucursalPartida.data);
                this.nuevoArriendo.idSucursalPartida = !!respuestaIdSucursalPartida.data;
                console.log("Validando ID de vehículo...");
                const respuestaIdVehiculo = await axios.get(
                    `http://localhost:8080/arriendo/validarVehiculo?id=${this.nuevoArriendo.idVehiculo}`
                );
                console.log("Respuesta del backend:", respuestaIdVehiculo.data);
                this.nuevoArriendo.idVehiculo = !!respuestaIdVehiculo.data;
                console.log("Validando monto a pagar...");
                const respuestaMontoPagar = await axios.get(
                    `http://localhost:8080/arriendo/validarMonto?monto=${this.nuevoArriendo.montoPagar}`
                );
                console.log("Respuesta del backend:", respuestaMontoPagar.data);
                this.nuevoArriendo.montoPagar = !!respuestaMontoPagar.data;

                return true;

            } catch (error) {
                console.error("Error al validar los datos:", error);
                alert("Error al validar los datos");
                return false;
            }
        },
    },
};
</script>

<style scoped>
    h2 {
        display: block;
        margin: 0 auto 2rem;
        position: absolute; /* Cambia la posición del logo */
        top: 33%; /* Ajusta la posición desde la parte superior */
        left: 3%;
    }
    form {
        display: block;
        margin: 0 auto 2rem;
        position: absolute; /* Cambia la posición del logo */
        top: 40%; /* Ajusta la posición desde la parte superior */
        left: 3%; /* Ajusta la posición desde la izquierda */
    }
</style>