package co.edu.uniandes.dse.parcialprueba.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class EspecialidadEntity extends BaseEntity {

    /*Cree la entidad EspecialidadEntity en la carpeta correspondiente. 
    Una especialidad tiene un nombre, una descripción 
    y un id de tipo Long que representa su llave primaria. 
    Tenga en cuenta que un médico puede tener varias especialiades y que una especialidad 
    puede ser ejercida por varios médicos.*/
    private String nombre;
    private String descripcion;

    @PodamExclude
    @ManyToMany(mappedBy = "especialidades") //Medico maneja la relación
    private List<MedicoEntity> medicos = new ArrayList<>();

}
