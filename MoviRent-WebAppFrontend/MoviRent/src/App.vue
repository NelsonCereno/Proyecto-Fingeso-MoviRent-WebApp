<script setup>
import { RouterLink, RouterView } from 'vue-router';
import { ref, onMounted } from 'vue';

const usuario = ref(null);

onMounted(() => {
  const userData = localStorage.getItem('usuario');
  if (userData) {
    usuario.value = JSON.parse(userData);
  }
});

const cerrarSesion = () => {
  localStorage.removeItem("usuario");
  usuario.value = null;
  alert("Sesión cerrada con éxito.");
  location.reload(); // Refresca para limpiar el estado
  window.location.href = "/"; // Redirige al Home
};

</script>

<template>
  <header>
    <div class="wrapper">
      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink v-if="!usuario" to="/iniciarSesion">Iniciar Sesión</RouterLink>
        <RouterLink v-if="usuario?.role === 'ADMINISTRADOR'" to="/crear-reporte">Crear Reporte</RouterLink>
        <RouterLink v-if="usuario?.role === 'ADMINISTRADOR'" to="/ver-reportes">Ver Reportes</RouterLink>
        <RouterLink v-if="usuario?.role === 'ADMINISTRADOR'" to="/crear-vehiculo">Crear Vehículo</RouterLink>
        <RouterLink to="/vehiculos">Ver Vehículos</RouterLink>
        <RouterLink to="/sobreNosotros">Sobre Nosotros</RouterLink>
        <button v-if="usuario" @click="cerrarSesion">Cerrar Sesión</button>
      </nav>
    </div>
    <div class="logos">
      <img alt="Vue logo" class="logo" src="@/assets/movirent.png" width="250" height="124.4" />
    </div>
  </header>

  <div class="container-web">
    <RouterView />
  </div>
</template>

<style scoped>
header {
  line-height: 1.5;
  max-height: 125px;
  top: 0%;
  left: 0%;
  width: 100%;
  position: absolute;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #181818;
}

.logos {
  display: flex;
  width: 25%;
  height: 100%;
  background-color: #181818;
  justify-content: center;
  align-items: center;
}

.wrapper {
  width: 75%;
  height: 100%;
  display: flex;
  background-color: #181818;
  justify-content: center;
  align-items: center;
}

.container-web {
  width: 100%;
  height: calc(100% - 125px);
  display: flex;
  position: absolute;
  justify-content: center;
  align-items: center;
  background-color: #345039;
  bottom: 0%;
  left: 0%;
}

nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-text);
}

nav a:first-of-type {
  border: 0;
}

button {
  padding: 5px 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>
