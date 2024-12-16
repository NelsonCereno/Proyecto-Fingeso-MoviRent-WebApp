<template>
  <div>
    <h2>Crear Arriendo</h2>
    <form @submit.prevent="crearArriendo">
      <div>
        <label for="fechaInicio">Fecha de Inicio:</label>
        <input type="date" id="fechaInicio" v-model="nuevoArriendo.fechaInicio" @change="calcularMonto" required />
      </div>
      <div>
        <label for="fechaTermino">Fecha de Término:</label>
        <input type="date" id="fechaTermino" v-model="nuevoArriendo.fechaTermino" @change="calcularMonto" required />
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
        <input type="number" id="idVehiculo" v-model="nuevoArriendo.idVehiculo" readonly />
      </div>
      <div>
        <label for="montoPagar">Monto a Pagar:</label>
        <input type="number" id="montoPagar" v-model="nuevoArriendo.montoPagar" readonly />
      </div>
      <button type="submit">Crear Arriendo</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";
import { ref, reactive, onMounted } from "vue";
import { useRoute } from "vue-router";

export default {
  name: "CrearArriendo",
  setup() {
    const route = useRoute();
    const nuevoArriendo = reactive({
      fechaInicio: "",
      fechaTermino: "",
      idSucursalLlegada: "",
      idSucursalPartida: "",
      idVehiculo: "",
      montoPagar: "",
    });
    const costoDiario = ref(0);

    onMounted(async () => {
  const idVehiculo = route.params.idVehiculo; // Ahora usa params en lugar de query
  if (idVehiculo) {
    try {
      const response = await axios.get(`http://localhost:8080/vehiculo/${idVehiculo}`);
      console.log("Datos del vehículo cargados:", response.data);

      // Llena los datos del arriendo con los datos del vehículo
      nuevoArriendo.idVehiculo = response.data.idVehiculo;
      costoDiario.value = parseFloat(response.data.precio) || 0; // Precio diario
      nuevoArriendo.montoPagar = costoDiario.value; // Inicializa el monto
    } catch (error) {
      console.error("Error al cargar el vehículo:", error);
      alert("No se pudo cargar la información del vehículo seleccionado.");
    }
  } else {
    alert("ID del vehículo no encontrado. Por favor, vuelve a intentarlo.");
  }
});

    const calcularMonto = () => {
      if (!nuevoArriendo.fechaInicio || !nuevoArriendo.fechaTermino) {
        return;
      }
      const fechaInicio = new Date(nuevoArriendo.fechaInicio);
      const fechaTermino = new Date(nuevoArriendo.fechaTermino);

      if (isNaN(fechaInicio.getTime()) || isNaN(fechaTermino.getTime())) {
        alert("Por favor, ingresa fechas válidas.");
        return;
      }
      if (fechaTermino <= fechaInicio) {
        alert("La fecha de término debe ser posterior a la de inicio.");
        return;
      }
      const diffTime = fechaTermino - fechaInicio;
      const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

      if (diffDays > 30) {
        alert("El arriendo no puede exceder los 30 días.");
        return;
      }
      nuevoArriendo.montoPagar = costoDiario.value * diffDays;
    };

    const crearArriendo = async () => {
      try {
        console.log("Datos enviados:", nuevoArriendo);
        const response = await axios.post("http://localhost:8080/arriendo/crear", nuevoArriendo);
        alert("Arriendo creado con éxito: " + response.data);
        limpiarFormulario();
      } catch (error) {
        console.error("Error al crear el arriendo:", error);
        alert("Error al crear el arriendo.");
      }
    };

    const limpiarFormulario = () => {
      nuevoArriendo.fechaInicio = "";
      nuevoArriendo.fechaTermino = "";
      nuevoArriendo.idSucursalLlegada = "";
      nuevoArriendo.idSucursalPartida = "";
      nuevoArriendo.idVehiculo = "";
      nuevoArriendo.montoPagar = "";
      costoDiario.value = 0;
    };

    return {
      nuevoArriendo,
      costoDiario,
      calcularMonto,
      crearArriendo,
    };
  },
};
</script>

<style scoped>
h2 {
  text-align: center;
}
form {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background: #f9f9f9;
}
form div {
  margin-bottom: 15px;
}
label {
  display: block;
  font-weight: bold;
}
input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
}
button {
  display: block;
  width: 100%;
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
