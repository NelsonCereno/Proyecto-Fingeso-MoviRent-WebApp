package Grupo_5.MoviRent_WebApp.ModuloUsuario.Controladores;

import Grupo_5.MoviRent_WebApp.ModuloUsuario.Entidades.EntidadUsuario;
import Grupo_5.MoviRent_WebApp.ModuloUsuario.Servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
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
    public ResponseEntity<String> crearUsuario(@RequestBody EntidadUsuario usuario) {
        // Verificar si el correo ya está registrado
        if (servicioUsuario.existeCorreo(usuario.getCorreo())) {
            // Si el correo ya está registrado, devolver un mensaje indicando que el correo está duplicado
            return ResponseEntity.ok("El correo ya está registrado. Intente con otro correo.");
        }

        // Si el correo no existe, se crea el usuario
        servicioUsuario.crearUsuario(usuario);
        logger.info("Usuario creado: " + usuario.getCorreo());
        return ResponseEntity.ok("Usuario creado con éxito: " + usuario.getId());
    }



    @GetMapping("/todos")
    public ResponseEntity<List<EntidadUsuario>> getAllUsuarios() {
        List<EntidadUsuario> usuarios = servicioUsuario.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }
}
