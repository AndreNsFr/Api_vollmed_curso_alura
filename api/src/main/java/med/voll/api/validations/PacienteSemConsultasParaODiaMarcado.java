package med.voll.api.validations;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = PacienteSemConsultasParaODiaMarcadoValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PacienteSemConsultasParaODiaMarcado {
    String message() default "O paciente não pode ter uma consulta em um dia que ja se tem uma consulta marcada para o mesmo paciente";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
