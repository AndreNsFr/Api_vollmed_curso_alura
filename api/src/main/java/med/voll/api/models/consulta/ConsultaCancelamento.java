package med.voll.api.models.consulta;

import jakarta.validation.constraints.NotNull;
import med.voll.api.validations.ConsultaExiste;
import med.voll.api.validations.UmDiaDeAntecedenciaParaCancelamento;

public record ConsultaCancelamento(
        @NotNull
        @ConsultaExiste
        @UmDiaDeAntecedenciaParaCancelamento
        String consultaId,
        @NotNull
        MotivoCancelamento motivoCancelamento
) {
}
