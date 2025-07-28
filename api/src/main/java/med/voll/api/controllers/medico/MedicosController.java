package med.voll.api.controllers.medico;


import jakarta.validation.Valid;
import med.voll.api.models.medico.DadosAtualizarMedico;
import med.voll.api.models.medico.DadosCadastroMedico;
import med.voll.api.models.medico.DadosDetalhamentoMedico;
import med.voll.api.models.medico.DadosListagemMedico;
import med.voll.api.repository.medico.Medico;
import med.voll.api.repository.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicosController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder UriBuilder){
        Medico medico = repository.save(new Medico(dados));

        var uri = UriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarMedico dados){
        Medico medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedico> detalharMedico(@PathVariable Long id){
        Medico medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        Medico medico = repository.getReferenceById(id);
        medico.inativar();
        return ResponseEntity.noContent().build();
    }

}
