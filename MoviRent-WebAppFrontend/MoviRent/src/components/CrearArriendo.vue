<template>
  <div class="form-container">

    <form @submit.prevent="crearArriendo">
      <h2>Crear Arriendo</h2>
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
        <label for="patente">Patente:</label>
        <input type="text" id="patente" v-model="nuevoArriendo.patente" readonly />
      </div>
      <div>
        <label for="modelo">Modelo:</label>
        <input type="text" id="modelo" v-model="nuevoArriendo.modelo" readonly />
      </div>
      <div>
        <label for="marca">Marca:</label>
        <input type="text" id="marca" v-model="nuevoArriendo.marca" readonly />
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
import { ref, reactive } from "vue";
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
      idVehiculo: route.query.idVehiculo || "",
      patente: route.query.patente || "",
      modelo: route.query.modelo || "",
      marca: route.query.marca || "",
      montoPagar: "",
      disponibilidad: route.query.disponibilidad || false,
    });
    const costoDiario = ref(parseFloat(route.query.precio) || 0);

    // Lógica para calcular el monto del arriendo
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

      //alert(nuevoArriendo.disponibilidad);

      // Verificar que el ID del vehículo esté presente
      if (!nuevoArriendo.idVehiculo) {
        alert("Por favor, ingresa un ID de vehículo válido.");
        return;
      }
      
      if((nuevoArriendo.disponibilidad).toString() == "false"){
        alert("El vehículo no está disponible para arrendar.");
        return;

      }else{
      
      try {
        // Buscar el vehículo en el backend con el ID
        const response = await axios.get(`http://localhost:8080/vehiculo/${nuevoArriendo.idVehiculo}`);
        console.log("Datos del vehículo cargados:", response.data);

        // Si el vehículo existe, cargar el costo diario
        costoDiario.value = parseFloat(response.data.precio) || 0;
        nuevoArriendo.montoPagar = costoDiario.value; // Inicializar el monto con el costo diario

        // Realizar la creación del arriendo
        const arriendoResponse = await axios.post("http://localhost:8080/arriendo/crear", nuevoArriendo);
        alert("Arriendo creado con éxito: " + arriendoResponse.data);
        nuevoArriendo.disponibilidad = false;
        //alert(nuevoArriendo.disponibilidad);
        limpiarFormulario();
      } catch (error) {
        console.error("Error al crear el arriendo:", error);
        
        if (error.response && error.response.status === 404) {
          // Verificamos si el error es por un ID de vehículo no encontrado (404)
          alert("Error al crear el arriendo. ID del vehículo inválido, por favor, intentalo nuevamente.");
        } else {
          // Si es otro tipo de error, mostramos un mensaje genérico
          alert("Error al crear el arriendo, por favor, intentalo nuevamente.");
        }
      }
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
.form-container{

}


form {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  background: #f6f8f7;
  border: 1px solid #f6f8f7;
  border-radius: 10px;
  color: #000000;
  text-align: left;
  overflow-y: auto;
  flex-wrap: wrap;
  
  /*ajustar para el vice*/
  max-height: 600px;
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
