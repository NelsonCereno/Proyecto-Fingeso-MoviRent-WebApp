import { createRouter, createWebHistory } from 'vue-router';
import Bienvenida from '../views/Bienvenida.vue';
import CrearReporte from '../components/CrearReporte.vue';
import CrearUsuario from '@/components/CrearUsuario.vue'; 
import VerVehiculos from '@/components/VerVehiculos.vue'; // Asegúrate de usar el nombre y ruta correctos
import CrearArriendo from '@/components/CrearArriendo.vue';
import VerReportes from '@/components/VerReporte.vue';
import CrearVehiculo from '@/components/CrearVehiculo.vue';
import EditarVehiculo from '@/components/EditarVehiculo.vue';
import IniciarSesion from '../components/IniciarSesion.vue';


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
    { path: '/ver-reportes', // Nueva ruta 
        name: 'verReportes', 
       component: VerReportes, // Vincula el componente VerReportes 
    },
    {
      path: '/crear-usuario', // Nueva ruta
      name: 'crearUsuario',
      component: CrearUsuario, // Vincula el componente CrearUsuario
    },
    {
      path: '/iniciarSesion',
      name: 'Iniciar Sesion',
      component: IniciarSesion,
    },
    { path: '/crear-vehiculo', 
       name: 'crearVehiculo', 
       component: CrearVehiculo, 
      },
    {
      path: '/vehiculos', 
      name: 'vehiculos',
      component: VerVehiculos, 
    },
    {
      path: '/arriendos/',
      name: 'arriendos',
      component: CrearArriendo,
    },
    {
      path: '/editar-vehiculo/:idVehiculo',
      name: 'editarVehiculo',
      component: EditarVehiculo,
    }
    
    
  ],
});

export default router;
