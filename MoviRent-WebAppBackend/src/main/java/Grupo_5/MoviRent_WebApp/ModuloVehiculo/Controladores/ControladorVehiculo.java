package Grupo_5.MoviRent_WebApp.ModuloVehiculo.Controladores;


import Grupo_5.MoviRent_WebApp.ModuloVehiculo.Entidades.EntidadVehiculo;
import Grupo_5.MoviRent_WebApp.ModuloVehiculo.Servicios.ServicioVehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
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
    public ResponseEntity<List<EntidadVehiculo>> obtenerPorDisponibilidad(@RequestParam(required = false) Boolean disponibilidad) {
        if (disponibilidad == null) {
            return ResponseEntity.badRequest().body(new ArrayList<>()); // Devuelve un error claro si falta el parámetro
        }
        List<EntidadVehiculo> vehiculos = servicioVehiculo.buscarPorDisponibilidad(disponibilidad);
        return ResponseEntity.ok(vehiculos);
    }

    //Filtrar por capacidad pasajeros
    @GetMapping("/capacidad")
    public ResponseEntity<List<EntidadVehiculo>> filtrarPorNroPasajeros(@RequestParam String nroPasajeros) {
        Integer pasajeros = Integer.parseInt(nroPasajeros);
        List<EntidadVehiculo> vehiculos = servicioVehiculo.buscarPorNroPasajeros(pasajeros);
        return ResponseEntity.ok(vehiculos);
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarVehiculo(@PathVariable Long id, @RequestBody EntidadVehiculo vehiculoActualizado) {
        System.out.println("ID recibido: " + id); // Log para depurar el ID recibido
        EntidadVehiculo vehiculoExistente = servicioVehiculo.buscarVehiculoPorId(id);
        if (vehiculoExistente == null) {
            System.out.println("Vehículo no encontrado con ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehículo no encontrado.");
        }

        vehiculoExistente.setDisponibilidad(vehiculoActualizado.getDisponibilidad());
        servicioVehiculo.agregarVehiculo(vehiculoExistente);

        return ResponseEntity.ok("Vehículo actualizado correctamente.");
    }



}
