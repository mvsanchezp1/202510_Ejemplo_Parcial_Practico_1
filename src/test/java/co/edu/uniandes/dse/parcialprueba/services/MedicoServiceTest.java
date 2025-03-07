package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import jakarta.transaction.Transactional;

@DataJpaTest
@Transactional
@Import({MedicoService.class})

public class MedicoServiceTest {
    
    @Autowired
    private MedicoService medicoService;

    @Autowired
    private TestEntityManager entityManager;
    private PodamFactory factory = new PodamFactoryImpl();

    @BeforeEach
    void setUp() {
        entityManager.getEntityManager().createQuery("delete from MedicoEntity").executeUpdate();
    }

    @Test
    void createMedicoTest() throws IllegalOperationException {
        MedicoEntity medico = factory.manufacturePojo(MedicoEntity.class);
        medico.setRegistroMedico("RM1234");

        MedicoEntity resultado = medicoService.createMedico(medico);
        
        assertNotNull(resultado);
        assertEquals("RM1234", resultado.getRegistroMedico());
        assertNotNull(entityManager.find(MedicoEntity.class, resultado.getId()));
    }

    @Test
    void createMedicoTestRegistroMedicoInvalido() {
        MedicoEntity medico = factory.manufacturePojo(MedicoEntity.class);
        medico.setRegistroMedico("1234");

        try {
            medicoService.createMedico(medico);
        } catch (IllegalOperationException e) {
            assertEquals("El registro médico debe iniciar con 'RM'.", e.getMessage());
        }
    }
}
