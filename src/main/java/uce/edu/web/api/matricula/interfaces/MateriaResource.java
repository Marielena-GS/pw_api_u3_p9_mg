package uce.edu.web.api.matricula.interfaces;

import java.util.List;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
public class MateriaResource {

    @Inject
    private MateriaService materiaService;

    @GET
    @Path("/todos")
    public List<Materia> listarTodos(){
        return this.materiaService.listarTodos();
    }

    @GET
    @Path("/consultarPorId/{id}")
    public Materia consultarPorId(@PathParam("id") Integer id){
        return this.materiaService.consultarPorId(id);
    }

    @GET
    @Path("/consultarPorCodigo/{codigo}")
    public Materia consultarPorCodigo(@PathParam("codigo") String codigo){
        return this.materiaService.consultarPorCodigo(codigo);
    }

    @POST
    @Path("/crear")
    public void guardar(Materia mat){
        this.materiaService.crear(mat);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizar(@PathParam("id") Integer id, Materia mat){
        this.materiaService.actualizar(id, mat);
    }

    @PATCH
    @Path("/actualizarParcial/{id}")
    public void actualizarParcial(@PathParam("id") Integer id, Materia mat){
        this.materiaService.actualizarParcial(id, mat);
    }

    @DELETE
    @Path("borrar/{id}")
    public void borrar(@PathParam("id") Integer id){
        this.materiaService.eliminar(id);
    }

    @DELETE
    @Path("borrarPorNombre/{nombre}")
    public void borrarPorNombre(@PathParam("nombre") String nombre){
        this.materiaService.eliminarPorNombre(nombre);
    }

}
