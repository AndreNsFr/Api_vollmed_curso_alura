package med.voll.api.validations;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ConsultaExisteValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConsultaExiste {
    String message() default "A consulta deve existir e também não estar cancelada";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
