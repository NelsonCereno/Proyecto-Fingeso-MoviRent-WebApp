import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import CrearReporte from '../components/CrearReporte.vue';
import CrearUsuario from '@/components/CrearUsuario.vue'; // Importa el componente CrearUsuario
import VerVehiculos from '@/components/VerVehiculos.vue'; // Asegúrate de usar el nombre y ruta correctos
import CrearArriendo from '@/components/CrearArriendo.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue'),
    },
    {
      path: '/crear-reporte',
      name: 'crearReporte',
      component: CrearReporte,
    },
    {
      path: '/crear-usuario', // Nueva ruta
      name: 'crearUsuario',
      component: CrearUsuario, // Vincula el componente CrearUsuario
    },
    {
      path: '/arriendos',
      name: 'arriendos',
      component: CrearArriendo,
    },
    {
      path: '/vehiculos', 
      name: 'vehiculos',
      component: VerVehiculos, 
    },
  ],
});

export default router;
