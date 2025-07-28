package med.voll.api.controllers.pacientes;

import jakarta.validation.Valid;
import med.voll.api.models.paciente.DadosAtualizarPaciente;
import med.voll.api.models.paciente.DadosCadastroPaciente;
import med.voll.api.models.paciente.DadosDetalhamentoPaciente;
import med.voll.api.models.paciente.DadosListagemPacientes;
import med.voll.api.repository.paciente.Paciente;
import med.voll.api.repository.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacientesController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPaciente(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        Paciente paciente = new Paciente(dados);

        repository.save(paciente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPacientes>> paginar(Pageable page) {
        Page<DadosListagemPacientes> responsePage = repository.findAllByAtivoTrue(page).map(DadosListagemPacientes::new);
        return ResponseEntity.ok(responsePage);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarPaciente(@RequestBody @Valid DadosAtualizarPaciente dados){
        Paciente paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> detalhamentoPaciente(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity inativarPaciente(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        return ResponseEntity.noContent().build();
    }

}
