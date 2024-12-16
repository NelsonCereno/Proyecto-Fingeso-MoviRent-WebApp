package Grupo_5.MoviRent_WebApp.ModuloArriendo.Controladores;

import Grupo_5.MoviRent_WebApp.ModuloArriendo.Entidades.EntidadArriendo;
import Grupo_5.MoviRent_WebApp.ModuloArriendo.Servicios.ServicioArriendo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@RestController
@RequestMapping("/arriendo")


public class ControladorArriendo {

    private final ServicioArriendo servicioArriendo;
    private static final Logger logger = Logger.getLogger(ControladorArriendo.class.getName());

    @Autowired
    public ControladorArriendo(ServicioArriendo servicioArriendo) {

        this.servicioArriendo = servicioArriendo;
    }

    //CRUD

    //Create
    @PostMapping("/crear")
    public String crearArriendo(@RequestBody EntidadArriendo arriendo) {
        servicioArriendo.crearArriendo(arriendo);
        logger.info("Arriendo creado"); //+ arriendo.getCorreo()
        return "Arriendo creado"; //+ arriendo.getId()
    }

    // Método para validar la fecha
    public class ArriendoController {

        @GetMapping("/arriendo/validarFecha")
        public ResponseEntity<?> validarFecha(@RequestParam String fecha) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            dateFormat.setLenient(false); // No permitir fechas ambiguas

            try {
                // Intentamos analizar la fecha
                Date parsedDate = dateFormat.parse(fecha);

                // Comprobamos si la fecha es anterior a la fecha actual
                if (parsedDate.before(new Date())) {
                    return ResponseEntity.badRequest().body("La fecha no puede ser anterior a la fecha actual.");
                }
                return ResponseEntity.ok("Fecha válida.");
            } catch (ParseException e) {
                // Si no se puede analizar la fecha, lanzamos un error
                return ResponseEntity.badRequest().body("Formato de fecha inválido. El formato correcto es MM/dd/yyyy.");
            }
        }
    }
}