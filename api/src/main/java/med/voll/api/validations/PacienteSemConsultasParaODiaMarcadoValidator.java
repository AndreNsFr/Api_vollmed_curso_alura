package med.voll.api.validations;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import med.voll.api.models.consulta.DadosAgendamentoConsulta;
import med.voll.api.repository.consultas.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PacienteSemConsultasParaODiaMarcadoValidator implements ConstraintValidator<PacienteSemConsultasParaODiaMarcado, DadosAgendamentoConsulta> {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public boolean isValid(DadosAgendamentoConsulta values, ConstraintValidatorContext context){

        LocalDateTime inicio_dia = values.dataHoraConsulta().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime fim_dia = inicio_dia.plusDays(1);

        return !consultaRepository.existsByPaciente_IdAndDataHoraConsultaBetween(
                Long.parseLong(values.paciente()),
                inicio_dia,
                fim_dia
        );

    }
}
