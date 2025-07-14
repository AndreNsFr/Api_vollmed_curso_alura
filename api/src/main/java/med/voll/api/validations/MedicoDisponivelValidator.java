package med.voll.api.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import med.voll.api.models.consulta.DadosAgendamentoConsulta;
import med.voll.api.repository.consultas.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class MedicoDisponivelValidator implements ConstraintValidator<MedicoDisponivel, DadosAgendamentoConsulta> {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public boolean isValid(DadosAgendamentoConsulta values, ConstraintValidatorContext context){
        if(values.medico() == null){
            return true;
        }

        return consultaRepository.existsByMedico_IdAndDataHoraConsultaBetween(
                Long.parseLong(values.medico()),
                values.dataHoraConsulta().minusHours(1),
                values.dataHoraConsulta()
        );
    }
}
