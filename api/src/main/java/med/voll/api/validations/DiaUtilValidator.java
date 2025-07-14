package med.voll.api.validations;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


import java.time.*;

public class DiaUtilValidator implements ConstraintValidator<DiaUtil, LocalDateTime> {

    private final LocalTime horaInicio = LocalTime.of(7,0);
    private final LocalTime horaFim = LocalTime.of(19,0);


    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context){
        if(value == null){
            return true;
        }

        LocalTime hora = value.toLocalTime();

        DayOfWeek diaDaSemana = value.getDayOfWeek();

        return !hora.isBefore(horaInicio) && !hora.isAfter(horaFim) && diaDaSemana != DayOfWeek.SUNDAY;
    }
}
