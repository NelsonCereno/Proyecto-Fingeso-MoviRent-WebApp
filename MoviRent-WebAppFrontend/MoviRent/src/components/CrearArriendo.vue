<template>
    <div>
      <h2>Crear Arriendo</h2>
      <form @submit.prevent="crearArriendo">
        <div>
          <label for="fechaInicio">Fecha de Inicio:</label>
          <input type="date" id="fechaInicio" v-model="nuevoArriendo.fechaInicio" @change="validarFechaInicio" required />
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
  import axios from 'axios';
  import { onMounted } from 'vue';
  import { useRoute } from 'vue-router';
  
  export default {
    name: 'CrearArriendo',
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
        costoDiario: 0,
      };
    },
    setup() {
      const route = useRoute();
      onMounted(() => {
        if (route.query.idVehiculo) {
          this.nuevoArriendo.idVehiculo = route.query.idVehiculo;
        }
      });
    },
    methods: {
      async crearArriendo() {
        try {
          console.log("Datos enviados:", this.nuevoArriendo);
          if(await this.comprobarDatos()) {
            console.log("Datos válidos, enviando datos...");
          } else {
            console.log("Datos inválidos, no se enviarán...");
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
        this.costoDiario = 0;
      },
      async comprobarDatos() {
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
          const fechaInicio = new Date(this.nuevoArriendo.fechaInicio);
          const fechaTermino = new Date(this.nuevoArriendo.fechaTermino);
          const diffTime = Math.abs(fechaTermino - fechaInicio);
          const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); 
          if (diffDays > 30) {
            alert("El arriendo no puede exceder los 30 días.");
            return false;
          }
          
          const respuestaIdSucursalLlegada = await axios.get(`http://localhost:8080/arriendo/validarSucursal?id=${this.nuevoArriendo.idSucursalLlegada}`);
          this.nuevoArriendo.idSucursalLlegada = !!respuestaIdSucursalLlegada.data;
          const respuestaIdSucursalPartida = await axios.get(`http://localhost:8080/arriendo/validarSucursal?id=${this.nuevoArriendo.idSucursalPartida}`);
          this.nuevoArriendo.idSucursalPartida = !!respuestaIdSucursalPartida.data;
          const respuestaIdVehiculo = await axios.get(`http://localhost:8080/arriendo/validarVehiculo?id=${this.nuevoArriendo.idVehiculo}`);
          this.nuevoArriendo.idVehiculo = !!respuestaIdVehiculo.data;
          const respuestaMontoPagar = await axios.get(`http://localhost:8080/arriendo/validarMonto?monto=${this.nuevoArriendo.montoPagar}`);
          this.nuevoArriendo.montoPagar = !!respuestaMontoPagar.data;
  
          return true;
  
        } catch (error) {
          console.error("Error al validar los datos:", error);
          alert("Error al validar los datos");
          return false;
        }
      },
      async calcularMonto() {
        if (this.nuevoArriendo.fechaInicio && this.nuevoArriendo.fechaTermino && this.nuevoArriendo.idVehiculo) {
          const fechaInicio = new Date(this.nuevoArriendo.fechaInicio);
          const fechaTermino = new Date(this.nuevoArriendo.fechaTermino);
          const diffTime = Math.abs(fechaTermino - fechaInicio);
          const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
          if (diffDays > 30) {
            alert("El arriendo no puede exceder los 30 días.");
            return;
          }
  
          try {
            const respuesta = await axios.get(`http://localhost:8080/vehiculo/${this.nuevoArriendo.idVehiculo}`);
            this.costoDiario = respuesta.data.precio;
            this.nuevoArriendo.montoPagar = this.costoDiario * diffDays;
          } catch (error) {
            console.error("Error al calcular el monto:", error);
            alert("Error al calcular el monto");
          }
        }
      },
      async validarFechaInicio() {
        try {
          const response = await axios.get(`http://localhost:8080/arriendo/validarFecha?fecha=${this.nuevoArriendo.fechaInicio}`);
          if (!response.data) {
            alert("La fecha de inicio no es válida.");
            this.nuevoArriendo.fechaInicio = "";
          }
        } catch (error) {
          console.error("Error al validar la fecha de inicio:", error);
          alert("Error al validar la fecha de inicio");
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
      left: 3%;
    }
  </style>
  