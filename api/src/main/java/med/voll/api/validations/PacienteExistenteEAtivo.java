package med.voll.api.validations;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PacienteExistenteEAtivoValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PacienteExistenteEAtivo {
    String message() default "O paciente deve existir e ser ativo";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
