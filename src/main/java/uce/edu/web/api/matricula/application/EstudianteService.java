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

    public List<EstudianteRepresentation> listarTodos(){
        List<EstudianteRepresentation> list=new ArrayList<>();
        for( Estudiante est: this.estudianteRepository.listAll()){
            list.add(this.mapperToER(est));
        }
        return list;
        
    }

    public EstudianteRepresentation consultarPorId(Integer id){
        return this.mapperToER(this.estudianteRepository.findById(id.longValue()));
    }
    @Transactional
    public void crear(Estudiante estu){
        this.estudianteRepository.persist(estu);
    }

    @Transactional
    public void actualizar(Integer id, Estudiante est){
        Estudiante estu = this.mapperToEstudiante(this.consultarPorId(id));
        estu.setApellido(est.getApellido());
        estu.setNombre(est.getNombre());
        estu.setFechaNacimiento(est.getFechaNacimiento());
    }

    @Transactional
    public void actualizarParcial(Integer id, Estudiante est){
        Estudiante estu=this.consultarPorId(id);
        if(est.getNombre()!=null){
            estu.setNombre(est.getNombre());
        }
        if(est.getApellido()!=null){
            estu.setApellido(est.getApellido());
        }
        if(est.getFechaNacimiento()!=null){
            estu.setFechaNacimiento(est.getFechaNacimiento());
        }
    }
    
    @Transactional
    public void eliminar(Integer id){
        this.estudianteRepository.deleteById(id.longValue());
    }

    public List<EstudianteRepresentation> buscarPorProvincia(String provincia, String genero){
        //return this.estudianteRepository.find("provincia", provincia).list();
        
                List<EstudianteRepresentation> list=new ArrayList<>();
        for( Estudiante est: this.estudianteRepository.find("provincia = ?1 and genero = ?2", provincia, genero).list()){
            list.add(this.mapperToER(est));
        }
        return list;
        
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
