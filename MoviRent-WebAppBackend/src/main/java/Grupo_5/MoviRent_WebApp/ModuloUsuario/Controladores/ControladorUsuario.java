package Grupo_5.MoviRent_WebApp.ModuloUsuario.Controladores;

import Grupo_5.MoviRent_WebApp.ModuloUsuario.Entidades.EntidadUsuario;
import Grupo_5.MoviRent_WebApp.ModuloUsuario.Servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/usuario")

//xdddxdxdxdx
public class ControladorUsuario {

    private final ServicioUsuario servicioUsuario;
    private static final Logger logger = Logger.getLogger(ControladorUsuario.class.getName());

    @Autowired
    public ControladorUsuario(ServicioUsuario servicioUsuario){

        this.servicioUsuario = servicioUsuario;
    }

    //CRUD

    //Create
    @PostMapping("/crear")
    public String crearUsuario(@RequestBody EntidadUsuario usuario){
        servicioUsuario.crearUsuario(usuario);
        logger.info("Usuario creado" ); //+ usuario.getCorreo()
        return "Usuario creado"; //+ usuario.getId()
    }



}
