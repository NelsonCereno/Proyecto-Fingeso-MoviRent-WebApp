package Grupo_5.MoviRent_WebApp.ModuloArriendo.Servicios;


import Grupo_5.MoviRent_WebApp.ModuloArriendo.Entidades.EntidadArriendo;
import Grupo_5.MoviRent_WebApp.ModuloArriendo.Repositorios.RepositorioArriendo;
import Grupo_5.MoviRent_WebApp.ModuloVehiculo.Entidades.EntidadVehiculo;
import Grupo_5.MoviRent_WebApp.ModuloVehiculo.Servicios.ServicioVehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioArriendo {


    private final RepositorioArriendo repositorioArriendo;

    @Autowired
    public ServicioArriendo(RepositorioArriendo repositorioArriendo) {

        this.repositorioArriendo = repositorioArriendo;
    }
    @Autowired
    private ServicioVehiculo servicioVehiculo;

    public void marcarVehiculoComoNoDisponible(Long idVehiculo) {
        EntidadVehiculo vehiculo = servicioVehiculo.buscarVehiculoPorId(idVehiculo);
        if (vehiculo != null) {
            vehiculo.setDisponibilidad(false);
            servicioVehiculo.agregarVehiculo(vehiculo);
        }
    }
    // CRUD

    //create
    public EntidadArriendo crearArriendo(EntidadArriendo arriendo){
        return repositorioArriendo.save(arriendo);
    }

    //read
    public EntidadArriendo getArriendoById(Long id){
        return
                repositorioArriendo.findById(id).orElse(null);
    }

    // Obtener todos los arriendos
    public List<EntidadArriendo> obtenerArriendos() {
        List<EntidadArriendo> arriendos = repositorioArriendo.findAll();

        // Asegura valores predeterminados para evitar nulos
        for (EntidadArriendo arriendo : arriendos) {
            if (arriendo.getFechaInicio() == null) arriendo.setFechaInicio("Fecha de inicio no especificada");
            if (arriendo.getFechaTermino() == null) arriendo.setFechaTermino("Fecha de término no especificada");
            if (arriendo.getIdSucursalLlegada() == null) arriendo.setIdSucursalLlegada(0L); // ID predeterminado
            if (arriendo.getIdSucursalPartida() == null) arriendo.setIdSucursalPartida(0L); // ID predeterminado
            if (arriendo.getIdVehiculo() == null) arriendo.setIdVehiculo(0L); // ID predeterminado
            if (arriendo.getMontoPagar() <= 0) arriendo.setMontoPagar(0); // Monto predeterminado
            if (arriendo.getLicencia() == null) arriendo.setLicencia("No registrada");
            if (arriendo.getUsuarioId() == null) arriendo.setUsuarioId(0L); // Usuario predeterminado
        }
        return arriendos;
    }


    //Update

    public EntidadArriendo updateArriendo(EntidadArriendo arriendo){
        return repositorioArriendo.save(arriendo);

    }

    public List<EntidadArriendo> getArriendosPorUsuario(Long idUsuario) {
        return repositorioArriendo.findByUsuarioId(idUsuario);
    }

    //Delete
    public void deleteArriendo(Long id){

        repositorioArriendo.deleteById(id);
    }
}