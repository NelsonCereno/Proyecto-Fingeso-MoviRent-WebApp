import { createRouter, createWebHistory } from 'vue-router';
import Bienvenida from '../views/Bienvenida.vue';
import CrearReporte from '../components/CrearReporte.vue';
import CrearUsuario from '@/components/CrearUsuario.vue'; // Importa el componente CrearUsuario
import VerVehiculos from '@/components/VerVehiculos.vue'; // Asegúrate de usar el nombre y ruta correctos

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Inicio',
      component: Bienvenida,
    },
    {
      path: '/SobreNosotros', 
      name: 'SobreNosotros',
      component: () => import('../views/SobreNosotros.vue'),
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
      path: '/vehiculos', 
      name: 'vehiculos',
      component: VerVehiculos, 
    },
  ],
});

export default router;
