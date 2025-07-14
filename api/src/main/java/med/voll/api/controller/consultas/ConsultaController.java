package med.voll.api.controller.consultas;

import jakarta.validation.Valid;
import med.voll.api.models.consulta.ConsultaCancelamento;
import med.voll.api.models.consulta.DadosAgendamentoConsulta;
import med.voll.api.repository.consultas.Consulta;
import med.voll.api.repository.consultas.ConsultaRepository;
import med.voll.api.repository.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping
    @Transactional
    public void cancelarConsulta(@RequestBody @Valid ConsultaCancelamento cancelamento){
        Consulta consulta = consultaRepository.getReferenceById(Long.parseLong(cancelamento.consultaId()));
        consulta.cancelamentoConsulta(cancelamento.motivoCancelamento());
    }
}
