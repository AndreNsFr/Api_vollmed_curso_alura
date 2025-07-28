package med.voll.api.controllers.consultas;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.models.consulta.DadosAgendamentoConsulta;
import med.voll.api.models.consulta.DadosDetalhamentoConsulta;
import med.voll.api.models.consulta.MotivoCancelamento;
import med.voll.api.repository.consultas.Consulta;
import med.voll.api.repository.consultas.ConsultaRepository;
import med.voll.api.repository.medico.MedicoRepository;
import med.voll.api.repository.paciente.PacienteRepository;
import med.voll.api.validations.ConsultaExiste;
import med.voll.api.validations.UmDiaDeAntecedenciaParaCancelamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(@RequestBody @Valid DadosAgendamentoConsulta consulta, UriComponentsBuilder uriBuilder){

        int vezesPercorridas = 0;
        Consulta consultaSalva;

        if(consulta.medico() == null){

            while (true){

                Long medicoAleatorio = medicoRepository.findAnyMedico().getId();

                boolean medicoLivre = consultaRepository.existsByMedico_IdAndDataHoraConsultaBetween(
                        medicoAleatorio,
                        consulta.dataHoraConsulta().minusHours(1),
                        consulta.dataHoraConsulta()
                );

                if(!medicoLivre){

                    DadosAgendamentoConsulta dadosAgendamentoConsulta = new DadosAgendamentoConsulta(
                            consulta.paciente(),
                            Long.toString(medicoAleatorio),
                            consulta.dataHoraConsulta());

                    consultaSalva = consultaRepository.save(new Consulta(dadosAgendamentoConsulta));

                    break;
                }

                if(vezesPercorridas > 20){
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("não existem médicos disponíveis para o dia escolhido.");
                }

                vezesPercorridas++;
            }
        }else{

            consultaSalva = consultaRepository.save(new Consulta(consulta));

        }

        var uri = uriBuilder.path("/consultas/{id}").buildAndExpand(consultaSalva.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoConsulta(consultaSalva));

    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoConsulta> detalhamentoConsulta (@PathVariable Long id){
        Consulta consulta = consultaRepository.getReferenceById(id);

        //Medico medico = medicoRepository.getReferenceById(consulta.getMedico().getId());

        //Paciente paciente = pacienteRepository.getReferenceById(consulta.getPaciente().getId());

        return ResponseEntity.ok(new DadosDetalhamentoConsulta(consulta));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity cancelarConsultaV2(@PathVariable @ConsultaExiste @UmDiaDeAntecedenciaParaCancelamento String id, @RequestParam @NotNull String motivo){
        Consulta consulta = consultaRepository.getReferenceById(Long.parseLong(id));

        try{
            consulta.cancelamentoConsulta(MotivoCancelamento.valueOf(motivo));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("O motivo está fora do padrão esperado.");
        }

        return ResponseEntity.noContent().build();

    }
}
