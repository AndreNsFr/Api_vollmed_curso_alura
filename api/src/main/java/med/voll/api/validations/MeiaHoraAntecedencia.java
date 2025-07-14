package med.voll.api.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;



@Documented
@Constraint(validatedBy = MeiaHoraAntecedenciaValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MeiaHoraAntecedencia {
    String message() default "O horário da consulta deve ter meia hora de antecedencia";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
