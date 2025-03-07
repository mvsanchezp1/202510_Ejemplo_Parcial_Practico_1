package co.edu.uniandes.dse.parcialprueba.services;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import({MedicoEspecialidadService.class})

public class MedicoEspecialidadServiceTest {
    @Autowired
    private MedicoEspecialidadService medicoEspecialidadService;

    @Autowired
    private TestEntityManager entityManager;
    private PodamFactory factory = new PodamFactoryImpl();
    private MedicoEntity medico;
    private EspecialidadEntity especialidad;


    @BeforeEach
    void setUp() {
        entityManager.getEntityManager().createQuery("delete from MedicoEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from EspecialidadEntity").executeUpdate();

        medico = factory.manufacturePojo(MedicoEntity.class);
        medico.setEspecialidades(new ArrayList<>());
        medico = entityManager.persist(medico);

        especialidad = factory.manufacturePojo(EspecialidadEntity.class);
        entityManager.persist(especialidad);
    }
    @Test
    void addEspecialidadTest() throws EntityNotFoundException {
        MedicoEntity resultado = medicoEspecialidadService.addEspecialidad(medico.getId(), especialidad.getId());

        assertNotNull(resultado);
        assertTrue(resultado.getEspecialidades().contains(especialidad));
    }

   
    @Test
    void addEspecialidadConMedicoInvalidoTest() {
        assertThrows(EntityNotFoundException.class, () -> {
            medicoEspecialidadService.addEspecialidad(999L, especialidad.getId());
        });
    }

   
    @Test
    void addEspecialidadConEspecialidadInvalidaTest() {
        assertThrows(EntityNotFoundException.class, () -> {
            medicoEspecialidadService.addEspecialidad(medico.getId(), 999L);
        });
    }
}

