package med.voll.api.validations;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import med.voll.api.repository.consultas.Consulta;
import med.voll.api.repository.consultas.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class UmDiaDeAntecedenciaParaCancelamentoValidator implements ConstraintValidator<UmDiaDeAntecedenciaParaCancelamento,String>{

    @Autowired
    private ConsultaRepository consultaRepository;


    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        Consulta consulta = consultaRepository.getReferenceById(Long.parseLong(value));

        LocalDateTime horaConsulta = consulta.getDataHoraConsulta();

        LocalDateTime agora = LocalDateTime.now();

        return !agora.isAfter(horaConsulta.minusDays(1));

    }
}
