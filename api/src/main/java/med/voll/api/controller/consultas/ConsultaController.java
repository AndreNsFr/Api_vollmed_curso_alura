package med.voll.api.controller.consultas;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.models.consulta.DadosAgendamentoConsulta;
import med.voll.api.models.consulta.MotivoCancelamento;
import med.voll.api.repository.consultas.Consulta;
import med.voll.api.repository.consultas.ConsultaRepository;
import med.voll.api.repository.medico.MedicoRepository;
import med.voll.api.validations.ConsultaExiste;
import med.voll.api.validations.UmDiaDeAntecedenciaParaCancelamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void agendarConsulta(@RequestBody @Valid DadosAgendamentoConsulta consulta){

        //consultaRepository.save(new Consulta(consulta));

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
                    consultaRepository.save(new Consulta(dadosAgendamentoConsulta));

                    break;
                }

            }
        }else{
            consultaRepository.save(new Consulta(consulta));
        }


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
