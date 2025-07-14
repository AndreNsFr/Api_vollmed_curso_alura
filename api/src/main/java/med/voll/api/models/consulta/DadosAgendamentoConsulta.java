package med.voll.api.models.consulta;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import med.voll.api.models.medico.DadosCadastroMedico;
import med.voll.api.validations.*;

import java.time.LocalDateTime;

@MedicoDisponivel // verifica se o medico está disponivel no horario
@PacienteSemConsultasParaODiaMarcado
public record DadosAgendamentoConsulta(
        @NotNull
        @PacienteExistenteEAtivo
        String paciente,

        @MedicoExistenteEAtivo

        String medico,

        @NotNull
        @FutureOrPresent
        @DiaUtil // das 7:00 às 19:00 de segunda à sábado
        @MeiaHoraAntecedencia // auto explicativo
        LocalDateTime dataHoraConsulta
) {
}
