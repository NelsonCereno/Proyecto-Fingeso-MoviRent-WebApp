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

    public List<EntidadArriendo> getAllArriendos(){
        return
                repositorioArriendo.findAll();
    }

    //Update

    public EntidadArriendo updateArriendo(EntidadArriendo arriendo){
        return repositorioArriendo.save(arriendo);

    }

    //Delete
    public void deleteArriendo(Long id){

        repositorioArriendo.deleteById(id);
    }
}