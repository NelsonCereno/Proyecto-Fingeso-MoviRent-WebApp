package Grupo_5.MoviRent_WebApp.ModuloVehiculo.Controladores;


import Grupo_5.MoviRent_WebApp.ModuloVehiculo.Entidades.EntidadVehiculo;
import Grupo_5.MoviRent_WebApp.ModuloVehiculo.Servicios.ServicioVehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
@RestController
@RequestMapping("/vehiculo")
public class ControladorVehiculo {

    private final ServicioVehiculo servicioVehiculo;
    private static final Logger logger = Logger.getLogger(ControladorVehiculo.class.getName());

    @Autowired
    public ControladorVehiculo(ServicioVehiculo servicioVehiculo) {
        this.servicioVehiculo = servicioVehiculo;
    }

    // Crear un vehículo
    @PostMapping("/crear")
    public ResponseEntity<String> crearVehiculo(@RequestBody EntidadVehiculo vehiculo) {
        servicioVehiculo.agregarVehiculo(vehiculo);
        logger.info("Vehículo creado: " + vehiculo.getPatente());
        return ResponseEntity.ok("Vehículo creado: " + vehiculo.getIdVehiculo());
    }

    // Obtener un vehículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntidadVehiculo> obtenerVehiculoPorId(@PathVariable Long id) {
        EntidadVehiculo vehiculo = servicioVehiculo.buscarVehiculoPorId(id);
        if (vehiculo == null) {
            logger.warning("Vehículo no encontrado con ID: " + id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehiculo);
    }

    // Obtener todos los vehículos
    @GetMapping("/todos")
    public ResponseEntity<List<EntidadVehiculo>> obtenerTodosLosVehiculos() {
        List<EntidadVehiculo> vehiculos = servicioVehiculo.obtenerVehiculos();
        return ResponseEntity.ok(vehiculos);
    }

    // Eliminar un vehículo
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarVehiculo(@PathVariable Long id) {
        servicioVehiculo.eliminarVehiculo(id);
        logger.info("Vehículo eliminado con ID: " + id);
        return ResponseEntity.ok("Vehículo eliminado con ID: " + id);
    }

    // Buscar por patente
    @GetMapping("/buscar/{patente}")
    public ResponseEntity<EntidadVehiculo> buscarVehiculoPorPatente(@PathVariable String patente) {
        EntidadVehiculo vehiculo = servicioVehiculo.buscarVehiculoPorPatente(patente);
        if (vehiculo == null) {
            logger.warning("Vehículo no encontrado con patente: " + patente);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehiculo);
    }

    // Filtrar por disponibilidad
    @GetMapping("/disponibles")
    public ResponseEntity<List<EntidadVehiculo>> filtrarPorDisponibilidad(@RequestParam Boolean disponible) {
        List<EntidadVehiculo> vehiculos = servicioVehiculo.buscarPorDisponibilidad(disponible);
        if (vehiculos.isEmpty()) {
            logger.info("No se encontraron vehículos con disponibilidad: " + disponible);
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(vehiculos);
    }

}
