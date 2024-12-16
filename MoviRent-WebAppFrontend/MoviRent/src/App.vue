<script setup>
import { RouterLink, RouterView } from 'vue-router';
</script>

<template>
  <header>
    <div class="wrapper">
      

      <!-- Navegación Única -->
      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/SobreNosotros">Sobre Nosotros</RouterLink>
        <RouterLink to="/crear-usuario">Crear Usuario</RouterLink>
        <RouterLink to="/crear-reporte">Crear Reporte</RouterLink>
        <RouterLink to="/ver-reportes">Ver Reportes</RouterLink>
        <RouterLink to="/arriendos">Crear Arriendo</RouterLink>
        <RouterLink to="/crear-vehiculo">Crear Vehículo</RouterLink>
        <RouterLink to="/vehiculos">Ver Vehículos</RouterLink> 

      </nav>
    </div>

    <div class="logos">
      <img alt="Vue logo" class="logo" src="@/assets/estafaPiramidal.jpg" width="250" height="125" />
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
  background-color: #07875c;
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
