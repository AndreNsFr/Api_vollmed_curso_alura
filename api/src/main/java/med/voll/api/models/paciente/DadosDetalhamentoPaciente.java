package med.voll.api.models.paciente;

import med.voll.api.repository.paciente.Endereco;
import med.voll.api.repository.paciente.Paciente;
import org.springframework.http.ResponseEntity;

public record DadosDetalhamentoPaciente(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco
) {
    public DadosDetalhamentoPaciente(Paciente paciente){
        this(paciente.getId(),paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
