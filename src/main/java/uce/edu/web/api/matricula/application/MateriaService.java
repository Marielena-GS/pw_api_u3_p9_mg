package uce.edu.web.api.matricula.application;

import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Materia;
import uce.edu.web.api.matricula.infraestrucuture.MateriaRepository;

@ApplicationScoped
public class MateriaService {

    @Inject
    private MateriaRepository materiaRepository;

    public List<Materia> listarTodos(){
        return this.materiaRepository.listAll();
    }

    public Materia consultarPorId(Integer id){
        return this.materiaRepository.findById(id.longValue());
    }

    public Materia consultarPorCodigo(String codigo){
        return this.materiaRepository.find("codigo_materia", codigo).firstResult();
    }

    @Transactional
    public void crear(Materia mat){
        this.materiaRepository.persist(mat);
    }

    @Transactional
    public void actualizar(Integer id, Materia mat){
        Materia materia = this.consultarPorId(id);
        materia.setNombre_materia(mat.getNombre_materia());
        materia.setDescripcion_materia(mat.getDescripcion_materia());
        materia.setCodigo_materia(mat.getCodigo_materia());
    }

    @Transactional
    public void actualizarParcial(Integer id, Materia mat){
        Materia materia=this.consultarPorId(id);
        if(mat.getNombre_materia()!=null){
            materia.setNombre_materia(mat.getNombre_materia());
        }
        if(mat.getDescripcion_materia()!=null){
            materia.setDescripcion_materia(mat.getDescripcion_materia());
        }
        if(mat.getCodigo_materia()!=null){
            materia.setCodigo_materia(mat.getCodigo_materia());
        }
    }

    @Transactional
    public void eliminar(Integer id){
        this.materiaRepository.deleteById(id.longValue());
    }

    @Transactional
    public void eliminarPorNombre(String nombre){
        this.materiaRepository.delete("nombre_materia", nombre);
    }




}
