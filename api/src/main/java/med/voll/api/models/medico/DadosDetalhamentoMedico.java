package med.voll.api.models.medico;

import med.voll.api.repository.medico.Endereco;
import med.voll.api.repository.medico.Medico;

public record DadosDetalhamentoMedico (
        Long id,
        String nome,
        String telefone,
        String email,
        String crm,
        Especialidade especialidade,
        Endereco endereco
){
    public DadosDetalhamentoMedico(Medico medico){
        this(medico.getId(),medico.getNome(),medico.getTelefone(),medico.getEmail(),medico.getCrm(),medico.getEspecialidade(),medico.getEndereco());
    }
}
