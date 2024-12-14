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