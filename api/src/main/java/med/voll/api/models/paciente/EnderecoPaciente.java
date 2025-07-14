package med.voll.api.models.paciente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoPaciente(
        @NotBlank
        String logradouro,

        String numero,
        String complemento,

        @NotBlank
        String bairro,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,
        @Pattern(regexp = "\\d{8}")
        @NotBlank
        String cep
) {



}
