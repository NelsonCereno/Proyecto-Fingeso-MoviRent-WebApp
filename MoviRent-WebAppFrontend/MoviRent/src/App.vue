
//npm install -g npm
//npm install axios
//npm run dev

// Funcionalidades de la página (JavaScript)
<script setup>
import { RouterLink, RouterView } from 'vue-router';
import HelloWorld from './components/HelloWorld.vue';
</script>

// Estructura base de la página (HTML)
<template>
  <header>

    <!-- Cargar imágenes -->
    <img alt="Vue logo" class="logo" src="@/assets/estafaPiramidal.jpg" width="250" height="125" />
    <img alt="Vue logo" class="logito" src="@/assets/tuto.png" width="125" height="125" />

    <!-- División del contenedor -->
    <div class="wrapper">
      <HelloWorld msg="Hola!" />

      <!-- Navegación -->
      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink>
        <RouterLink to="/crear-reporte">Crear Reporte</RouterLink>
        <RouterLink to="/crear-usuario">Crear Usuario</RouterLink>
        <RouterLink to="/vehiculos">Ver Vehículos</RouterLink> 
      </nav>
      
    </div>
  </header>

  <RouterView />
</template>

// Estética de la página (CSS)
<style scoped>
header {
  line-height: 1.5;
  max-height: 100vh;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
  position: absolute; /* Cambia la posición del logo */
  top: 2%; /* Ajusta la posición desde la parte superior */
  left: 78%; /* Ajusta la posición desde la izquierda */
}

.logito{
  display: block;
  margin: 0 auto 2rem;
  position: absolute; /* Cambia la posición del logo */
  top: 2%; /* Ajusta la posición desde la parte superior */
  left: 65%; /* Ajusta la posición desde la izquierda */
}

.wrapper {
  display: absolute;
  position: absolute; /* Cambia la posición del contenedor */
  top: 0%; /* Ajusta la posición desde la parte superior */
  left: 3%; /* Ajusta la posición desde la izquierda */
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
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
  }
}
</style>

