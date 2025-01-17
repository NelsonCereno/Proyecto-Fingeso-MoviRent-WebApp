package Grupo_5.MoviRent_WebApp.ModuloReporte.Servicios;

import Grupo_5.MoviRent_WebApp.ModuloReporte.Entidades.EntidadReporte;
import Grupo_5.MoviRent_WebApp.ModuloReporte.Repositorios.RepositorioReporte;
import Grupo_5.MoviRent_WebApp.ModuloUsuario.Entidades.EntidadUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioReporte {

    private final RepositorioReporte reporteRepository;

    @Autowired
    public ServicioReporte(RepositorioReporte reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    public void crearReporte(EntidadReporte reporte) {
        reporteRepository.save(reporte);
    }

    //obtener todos los usuarios
    public List<EntidadReporte> getAllReportes() {
        return reporteRepository.findAll();
    }
}
