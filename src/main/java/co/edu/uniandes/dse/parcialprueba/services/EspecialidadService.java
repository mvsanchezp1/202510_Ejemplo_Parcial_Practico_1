package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    /*Cree la clase correspondiente para la lógica de especialidad. 
    Implemente unicamente el método createEspecialidad. 
    Valide que la descripción tenga como mínimo 10 caracteres. */
    @Transactional
    public EspecialidadEntity createEspecialidad(EspecialidadEntity especialidad) throws IllegalOperationException {
        if (especialidad.getDescripcion().length() < 10) {
            throw new IllegalOperationException ("La descripción debe tener al menos 10 caracteres.");
        }
        especialidad = especialidadRepository.save(especialidad);
        return especialidad;
    }
}
