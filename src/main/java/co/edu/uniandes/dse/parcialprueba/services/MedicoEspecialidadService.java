package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoEspecialidadService {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private EspecialidadRepository especialidadRepository;

    /* Cree la clase correspondiente para la lógica de la asociación 
    entre médico y especialidad. Implemente unicamente el método addEspecialidad. 
    Este método recibe como parámetro el id del médico, el id de la especidad y 
    le agrega la especialidad al médico. 
    Valide que tanto el médico como la especialidad existan.*/

    @Transactional
    public MedicoEntity addEspecialidad(Long idMedico, Long idEspecialidad) throws EntityNotFoundException {
        log.info("Inicia proceso de agregar una especialidad con id {} al médico con id {}", idMedico, idEspecialidad);
        Optional<MedicoEntity> medicoEntity = medicoRepository.findById(idMedico);
        if (medicoEntity.isEmpty()) {
            throw new EntityNotFoundException("No existe un médico con el id: " + idMedico);
        }

        Optional<EspecialidadEntity> especialidadEntity = especialidadRepository.findById(idEspecialidad);
        if (especialidadEntity.isEmpty()) {
            throw new EntityNotFoundException("No existe una especialidad con el id: " + idEspecialidad);
        }

        medicoEntity.get().getEspecialidades().add(especialidadEntity.get());
        especialidadEntity.get().getMedicos().add(medicoEntity.get());
        medicoRepository.save(medicoEntity.get());
        especialidadRepository.save(especialidadEntity.get());

        log.info("Termina proceso de agregar una especialidad con id {} al médico con id {}", idMedico, idEspecialidad);
        return medicoEntity.get();

}
}
