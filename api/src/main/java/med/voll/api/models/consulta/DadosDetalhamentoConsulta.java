package med.voll.api.models.consulta;

import med.voll.api.repository.consultas.Consulta;
import med.voll.api.repository.medico.Medico;
import med.voll.api.repository.paciente.Paciente;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(
        Long id,
        Long medicoId,
        Long pacienteId,
        LocalDateTime dataHoraConsulta,
        MotivoCancelamento motivoCancelamento,
        boolean ativa
) {



    public DadosDetalhamentoConsulta(Consulta consulta){

        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getDataHoraConsulta(),consulta.getMotivoCancelamento(), consulta.isAtiva());

    }
}
