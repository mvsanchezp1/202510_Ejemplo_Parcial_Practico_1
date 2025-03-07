package co.edu.uniandes.dse.parcialprueba.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class MedicoEntity extends BaseEntity {

/* Un médico tiene un nombre, un apellido, 
un registro médico y un id de tipo Long que 
representa su llave primaria. Tenga en cuenta que un médico puede tener varias especialiades y que una especialidad puede ser ejercida por varios médicos. */

    private String nombre;
    private String apellido;
    private String registroMedico;
    
    @PodamExclude
    @ManyToMany 
    @JoinTable(name = "medico_especialidad",
    joinColumns = @jakarta.persistence.JoinColumn(name = "medico_id"), //Tabla intermedia
    inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "especialidad_id")) // FK de medico
    private List<EspecialidadEntity> especialidades = new ArrayList<>(); // FK de especialidad
    
    
}
