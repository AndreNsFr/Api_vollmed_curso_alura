package med.voll.api.controller.pacientes;

import jakarta.validation.Valid;
import med.voll.api.models.paciente.DadosAtualizarPaciente;
import med.voll.api.models.paciente.DadosCadastroPaciente;
import med.voll.api.models.paciente.DadosListagemPacientes;
import med.voll.api.repository.medico.Medico;
import med.voll.api.repository.paciente.Paciente;
import med.voll.api.repository.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacientesController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarPaciente(@RequestBody @Valid DadosCadastroPaciente dados) {
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListagemPacientes> paginar(Pageable page) {
        return repository.findAllByAtivoTrue(page).map(DadosListagemPacientes::new);
    }

    @PutMapping
    @Transactional
    public void atualizarPaciente(@RequestBody @Valid DadosAtualizarPaciente dados){
        Paciente paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void inativarPaciente(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        paciente.inativar();
    }

}
