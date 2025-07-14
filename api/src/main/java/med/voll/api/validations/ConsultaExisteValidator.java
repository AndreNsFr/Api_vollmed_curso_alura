package med.voll.api.validations;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import med.voll.api.repository.consultas.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class ConsultaExisteValidator implements ConstraintValidator<ConsultaExiste, String> {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        return consultaRepository.existsByIdAndAtivaTrue(Long.parseLong(value));
    }
}
