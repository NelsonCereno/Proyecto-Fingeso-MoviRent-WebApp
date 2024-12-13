package Grupo_5.MoviRent_WebApp.ModuloReporte.Controladores;

import Grupo_5.MoviRent_WebApp.ModuloReporte.Entidades.EntidadReporte;
import Grupo_5.MoviRent_WebApp.ModuloReporte.Servicios.ServicioReporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reportes")
public class ControladorReporte {

    private final ServicioReporte servicioReporte;

    @Autowired
    public ControladorReporte(ServicioReporte servicioReporte) {
        this.servicioReporte = servicioReporte;
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearReporte(@RequestBody EntidadReporte reporte) {
        servicioReporte.crearReporte(reporte);
        return ResponseEntity.ok("Reporte creado: " + reporte.getIdReporte());
    }
}
