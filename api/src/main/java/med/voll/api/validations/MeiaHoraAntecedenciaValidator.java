package med.voll.api.validations;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;


public class MeiaHoraAntecedenciaValidator implements ConstraintValidator<MeiaHoraAntecedencia, LocalDateTime>{

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context){

        if(value == null){
            return true;
        }


        LocalDateTime horaAtual = LocalDateTime.now();
        horaAtual = horaAtual.plusMinutes(30);

        return value.isAfter(horaAtual);


    }
}
