package med.voll.api.validations;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UmDiaDeAntecedenciaParaCancelamentoValidator.class )
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UmDiaDeAntecedenciaParaCancelamento {
    String message() default "A consulta deve ser cancelada com uma antecedencia de 24 horas";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
