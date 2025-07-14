package med.voll.api.validations;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MedicoDisponivelValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MedicoDisponivel {
    String message() default "O médico deve estar disponivel no horario da consulta";
    Class<?>[] groups() default {};
    Class<?extends Payload>[] payload() default {};
}
