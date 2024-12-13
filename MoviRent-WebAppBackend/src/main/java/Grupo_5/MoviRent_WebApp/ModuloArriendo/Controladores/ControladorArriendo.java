package Grupo_5.MoviRent_WebApp.ModuloArriendo.Controladores;

import Grupo_5.MoviRent_WebApp.ModuloArriendo.Entidades.EntidadArriendo;
import Grupo_5.MoviRent_WebApp.ModuloArriendo.Servicios.ServicioArriendo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/arriendo")


public class ControladorArriendo {

    private final ServicioArriendo servicioArriendo;
    private static final Logger logger = Logger.getLogger(ControladorArriendo.class.getName());

    @Autowired
    public ControladorArriendo(ServicioArriendo servicioArriendo){

        this.servicioArriendo = servicioArriendo;
    }

    //CRUD

    //Create
    @PostMapping("/crear")
    public String crearArriendo(@RequestBody EntidadArriendo arriendo){
        servicioArriendo.crearArriendo(arriendo);
        logger.info("Arriendo creado" ); //+ arriendo.getCorreo()
        return "Arriendo creado"; //+ arriendo.getId()
    }



}
