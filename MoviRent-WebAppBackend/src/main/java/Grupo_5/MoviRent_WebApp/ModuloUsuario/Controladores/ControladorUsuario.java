package Grupo_5.MoviRent_WebApp.ModuloUsuario.Controladores;

import Grupo_5.MoviRent_WebApp.ModuloUsuario.Entidades.EntidadUsuario;
import Grupo_5.MoviRent_WebApp.ModuloUsuario.Servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/usuario")


public class ControladorUsuario {

    private final ServicioUsuario servicioUsuario;
    private static final Logger logger = Logger.getLogger(ControladorUsuario.class.getName());
    @Autowired
    public ControladorUsuario(ServicioUsuario servicioUsuario){

        this.servicioUsuario = servicioUsuario;
    }

    //CRUD

    @PostMapping("/crear")
    public ResponseEntity<String> crearUsuario(@RequestBody EntidadUsuario usuario) {
        try {
            if (servicioUsuario.existeCorreo(usuario.getCorreo())) {
                return ResponseEntity.ok("El correo ya está registrado. Intente con otro correo.");
            }

            // Validaciones de formato
            String fechaNacimientoRegex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
            if (!usuario.getFechaNacimiento().matches(fechaNacimientoRegex)) {
                logger.warning("Fecha de nacimiento inválida: " + usuario.getFechaNacimiento());
                return ResponseEntity.badRequest().body("Fecha de nacimiento no válida. El formato correcto es DD/MM/YYYY.");
            }

            // Validación del rol
            if (usuario.getRole() == null) {
                logger.warning("Rol no especificado.");
                return ResponseEntity.badRequest().body("El rol es obligatorio. Debe ser 'ADMINISTRADOR' o 'CLIENTE'.");
            }
            // Crear usuario
            servicioUsuario.crearUsuario(usuario);
            logger.info("Usuario creado: " + usuario.getCorreo() + " con rol: " + usuario.getRole());
            return ResponseEntity.ok("Usuario creado con éxito: " + usuario.getId());

        } catch (Exception e) {
            logger.severe("Error al crear usuario: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el usuario.");
        }
    }



    @PostMapping("/iniciarSesion")
    public ResponseEntity<?> loginUsuario(@RequestParam String correo, @RequestParam String contrasena) {
        EntidadUsuario usuario = servicioUsuario.getUsuarioByCorreo(correo);
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            // Devolver un objeto con ID y rol
            Map<String, Object> response = new HashMap<>();
            response.put("id", usuario.getId());
            response.put("role", usuario.getRole().toString());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body("Correo o contraseña incorrectos.");
        }
    }



    @GetMapping("/todos")
    public ResponseEntity<List<EntidadUsuario>> getAllUsuarios() {
        List<EntidadUsuario> usuarios = servicioUsuario.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }
}
