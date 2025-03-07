package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class MedicoService {
    /*Cree la clase correspondiente para la lógica de médico. 
    Implemente unicamente el método createMedicos. Valide que el registro 
    médico inicie solo con los caracteres "RM" (e.g., RM1745). */
    @Autowired
    private MedicoRepository medicoRepository;
    
     /**
     * Crea un nuevo médico en la base de datos.
     * @param medico Entidad del médico a crear.
     * @return El médico creado.
     * @throws BusinessLogicException Si el registro médico no inicia con "RM".
     */
    @Transactional
    public MedicoEntity createMedico(MedicoEntity medico) throws IllegalOperationException {
        
        if (!medico.getRegistroMedico().startsWith("RM")) {
            throw new IllegalOperationException ("El registro médico debe iniciar con 'RM'.");
        }
        medico = medicoRepository.save(medico);
        return medico;
    }

}
