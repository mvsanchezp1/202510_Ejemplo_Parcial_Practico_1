package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(EspecialidadService.class)
public class EspecialidadServiceTest {

    @Autowired
    private EspecialidadService especialidadService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

   
    @BeforeEach
    void setUp() {
        entityManager.getEntityManager().createQuery("DELETE FROM EspecialidadEntity").executeUpdate();
    }

   
    @Test
    void createEspecialidadTest() throws IllegalOperationException {
        EspecialidadEntity especialidad = factory.manufacturePojo(EspecialidadEntity.class);
        especialidad.setDescripcion("Especialidad en cardiología"); // ✅ Descripción válida

        EspecialidadEntity resultado = especialidadService.createEspecialidad(especialidad);

        assertNotNull(resultado);
        assertEquals("Especialidad en cardiología", resultado.getDescripcion());
        assertNotNull(entityManager.find(EspecialidadEntity.class, resultado.getId()));
    }

    
    @Test
    void createEspecialidadTestDescripcionInvalida() {
        EspecialidadEntity especialidad = factory.manufacturePojo(EspecialidadEntity.class);
        especialidad.setDescripcion("Corta"); // ❌ Descripción con menos de 10 caracteres

        assertThrows(IllegalOperationException.class, () -> {
            especialidadService.createEspecialidad(especialidad);
        });
    }
}
