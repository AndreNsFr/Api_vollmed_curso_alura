package med.voll.api.validations;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import med.voll.api.repository.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // <- ESSENCIAL
public class MedicoExistenteEAtivoValidator implements ConstraintValidator<MedicoExistenteEAtivo, String >{

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public boolean isValid(String id, ConstraintValidatorContext context){

        if(id == null){
            return true;
        }

        Long long_id = Long.parseLong(id);


        return medicoRepository.existsByIdAndAtivoTrue(long_id);
    }
}
