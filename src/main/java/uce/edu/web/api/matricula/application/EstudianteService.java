package uce.edu.web.api.matricula.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestrucuture.EstudianteRepository;

@ApplicationScoped
public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<EstudianteRepresentation> listarTodos() {
        List<EstudianteRepresentation> list = new ArrayList<>();
        List<Estudiante> listE = this.estudianteRepository.listAll();
        for (Estudiante estudiante : listE) {
            list.add(mapperToER(estudiante));
        }
        return list;
    }

     public EstudianteRepresentation consultarPorId(Integer id) {
        return this.mapperToER(estudianteRepository.findById(id.longValue()));
    }

    @Transactional
    public void crear(EstudianteRepresentation estu) {
        this.estudianteRepository.persist(this.mapperToEstudiante(estu));
    }

    @Transactional
    public void actualizar(Integer id, EstudianteRepresentation estudiante) {

        Estudiante est = estudianteRepository.findById(id.longValue());
        est.setApellido(estudiante.apellido);
        est.setNombre(estudiante.nombre);
        est.setFechaNacimiento(estudiante.fechaNacimiento);
        // hibernate actualiza directamente el estudiante por dity cheking
    }

     @Transactional
    public void actualizarParcial(Integer id, EstudianteRepresentation estudiante) {
        Estudiante est = estudianteRepository.findById(id.longValue());
        if (estudiante.nombre != null) {
            est.setApellido(estudiante.apellido);
        }
        if (estudiante.apellido!= null) {
            est.setApellido(estudiante.apellido);
        }
        if (estudiante.fechaNacimiento != null) {
            est.setFechaNacimiento(estudiante.fechaNacimiento);
        }
        // hibernate actualiza directamente el estudiante por dity cheking
    }
    
    @Transactional
    public void eliminar(Integer id) {
        estudianteRepository.deleteById(id.longValue());
    }

    // metodo panache para optimizar codigo
    public List<EstudianteRepresentation> buscarPorProvincia(String provincia, String genero) {
        // return estudianteRepository.find("provincia", provincia).list();
        List<Estudiante> list = estudianteRepository.find("provincia = ?1 and genero =?2", provincia, genero).list();
        List<EstudianteRepresentation> list2 = new ArrayList<>();
        for (Estudiante estudiante : list) {
            list2.add(this.mapperToER(estudiante));
        }
        return list2;
    }

    private EstudianteRepresentation mapperToER(Estudiante est){
        EstudianteRepresentation estuR = new EstudianteRepresentation();
        estuR.id = est.getId();
        estuR.nombre = est.getNombre();
        estuR.apellido = est.getApellido();
        estuR.fechaNacimiento = est.getFechaNacimiento();
        estuR.genero = est.genero;
        estuR.provincia = est.provincia;

        return estuR;
    }

     private Estudiante mapperToEstudiante(EstudianteRepresentation est){
        Estudiante estuR = new Estudiante();
        estuR.setId(est.id);
        estuR.setApellido(est.apellido);
        estuR.setNombre(est.nombre);
        estuR.setFechaNacimiento(est.fechaNacimiento);
        estuR.genero = est.genero;
        estuR.provincia = est.provincia;
        return estuR;
    }
}
