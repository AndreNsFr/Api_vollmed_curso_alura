package med.voll.api.repository.consultas;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.models.consulta.ConsultaCancelamento;
import med.voll.api.models.consulta.DadosAgendamentoConsulta;
import med.voll.api.models.consulta.MotivoCancelamento;
import med.voll.api.repository.medico.Medico;
import med.voll.api.repository.paciente.Paciente;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
    @Column(name = "data_hora_consulta")
    private LocalDateTime dataHoraConsulta;

    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;

    @Column(name = "ativa")
    private boolean ativa;


    public Consulta(DadosAgendamentoConsulta consulta){
        this.paciente = new Paciente();
        this.paciente.setId(Long.parseLong(consulta.paciente()));

        this.medico = new Medico();
        this.medico.setId(Long.parseLong(consulta.medico()));

        this.dataHoraConsulta = consulta.dataHoraConsulta();

        this.ativa = true;
    }

    public void cancelamentoConsulta(MotivoCancelamento motivoDoCancelamento){

        this.motivoCancelamento = motivoDoCancelamento;
        this.ativa = false;
    }
}
