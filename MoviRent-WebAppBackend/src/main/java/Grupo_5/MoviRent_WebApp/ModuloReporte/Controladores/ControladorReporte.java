package Grupo_5.MoviRent_WebApp.ModuloReporte.Controladores;

import Grupo_5.MoviRent_WebApp.ModuloReporte.Entidades.EntidadReporte;
import Grupo_5.MoviRent_WebApp.ModuloReporte.Servicios.ServicioReporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ControladorReporte {

    @Autowired
    private ServicioReporte servicioReporte;

    @PostMapping("/crear")
    public ResponseEntity<String> crearReporte(@RequestBody EntidadReporte reporte) {
        servicioReporte.crearReporte(reporte);
        return ResponseEntity.ok("Reporte creado con éxito.");
    }

    @GetMapping("/todos")
    public ResponseEntity<List<EntidadReporte>> obtenerTodosLosReportes() {
        List<EntidadReporte> reportes = servicioReporte.getAllReportes();
        return ResponseEntity.ok(reportes);
    }

    // Otros métodos del controlador
}
