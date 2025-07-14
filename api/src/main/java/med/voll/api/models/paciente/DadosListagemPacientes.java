package med.voll.api.models.paciente;

import med.voll.api.repository.paciente.Paciente;

public record DadosListagemPacientes(
        String nome,
        String email,
        String cpf
) {

    public DadosListagemPacientes(Paciente paciente){
        this(paciente.getNome(),paciente.getEmail(),paciente.getCpf());
    }

}
