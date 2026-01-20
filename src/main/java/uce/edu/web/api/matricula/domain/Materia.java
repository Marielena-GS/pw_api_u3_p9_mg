package uce.edu.web.api.matricula.domain;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "Materia")
@SequenceGenerator(name = "materia_seq",sequenceName = "materia_secuencia", allocationSize = 1)
public class Materia extends PanacheEntityBase{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "materia_seq")
    private Long id;
    private String nombre_materia;
    private String descripcion_materia;
    private String codigo_materia;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre_materia() {
        return nombre_materia;
    }
    public void setNombre_materia(String nombre_materia) {
        this.nombre_materia = nombre_materia;
    }
    public String getDescripcion_materia() {
        return descripcion_materia;
    }
    public void setDescripcion_materia(String descripcion_materia) {
        this.descripcion_materia = descripcion_materia;
    }
    public String getCodigo_materia() {
        return codigo_materia;
    }
    public void setCodigo_materia(String codigo_materia) {
        this.codigo_materia = codigo_materia;
    }
    
    


}
