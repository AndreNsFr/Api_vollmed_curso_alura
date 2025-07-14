package med.voll.api.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = MedicoExistenteEAtivoValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MedicoExistenteEAtivo {
    String message() default "O medico deve existir e estar ativo";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
