package med.voll.api.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.time.LocalDateTime;


@Documented
@Constraint(validatedBy = DiaUtilValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DiaUtil {

    String message() default "O horario deve estar entre as 7:00 e 19:00 e também entre segunda e sabado";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
