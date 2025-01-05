import { createRouter, createWebHistory } from "vue-router";
import Bienvenida from "../views/Bienvenida.vue";
import CrearReporte from "../components/CrearReporte.vue";
import CrearUsuario from "@/components/CrearUsuario.vue";
import VerVehiculos from "@/components/VerVehiculos.vue";
import CrearArriendo from "@/components/CrearArriendo.vue";
import VerReportes from "@/components/VerReporte.vue";
import CrearVehiculo from "@/components/CrearVehiculo.vue";
import EditarVehiculo from "@/components/EditarVehiculo.vue";
import IniciarSesion from "../components/IniciarSesion.vue";
import SobreNosotros from "../views/SobreNosotros.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "Inicio",
      component: Bienvenida, // Página principal es Login
    },
    {
      path: "/sobreNosotros",
      name: "SobreNosotros",
      component: SobreNosotros,
    },
    {
      path: "/crear-reporte",
      name: "crearReporte",
      component: CrearReporte,
      meta: { roles: ["ADMINISTRADOR"] },
    },
    {
      path: "/ver-reportes",
      name: "verReportes",
      component: VerReportes,
      meta: { roles: ["ADMINISTRADOR"] },
    },
    {
      path: "/crearUsuario",
      name: "crearUsuario",
      component: CrearUsuario,
    },
    {
      path: "/crear-vehiculo",
      name: "crearVehiculo",
      component: CrearVehiculo,
      meta: { roles: ["ADMINISTRADOR"] },
    },
    {
      path: "/vehiculos",
      name: "vehiculos",
      component: VerVehiculos,
    },
    {
      path: "/arriendos/",
      name: "arriendos",
      component: CrearArriendo,
      meta: { roles: ["ADMINISTRADOR", "CLIENTE"] },
    },
    {
      path: "/editar-vehiculo/:idVehiculo",
      name: "editarVehiculo",
      component: EditarVehiculo,
      meta: { roles: ["ADMINISTRADOR"] },
    },
    {
      path: '/iniciarSesion',
      name: 'iniciarSesion',
      component: IniciarSesion,
    },
  ],
});

// Middleware de autenticación
router.beforeEach((to, from, next) => {
  const usuario = JSON.parse(localStorage.getItem("usuario"));
  if (to.meta.roles && (!usuario || !to.meta.roles.includes(usuario.role))) {
    if (!usuario) {
      next({ name: "Inicio" });
    } else {
      alert("Acceso denegado.");
      next(false);
    }
  } else {
    next();
  }
});

export default router;
