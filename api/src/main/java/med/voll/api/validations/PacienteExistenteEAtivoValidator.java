package med.voll.api.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import med.voll.api.repository.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // <- ESSENCIAL
public class PacienteExistenteEAtivoValidator implements ConstraintValidator<PacienteExistenteEAtivo,String>{

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public boolean isValid(String id, ConstraintValidatorContext context){

        if(id == null){
            return true;
        }

        Long long_id = Long.parseLong(id);



        return pacienteRepository.existsByIdAndAtivoTrue(long_id);

    }
}
