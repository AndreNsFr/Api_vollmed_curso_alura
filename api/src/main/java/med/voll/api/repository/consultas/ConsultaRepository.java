package med.voll.api.repository.consultas;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta,Long> {

    boolean existsByMedico_IdAndDataHoraConsultaBetween(Long medicoId, LocalDateTime inicio, LocalDateTime fim);
    boolean existsByPaciente_IdAndDataHoraConsultaBetween(Long pacienteId, LocalDateTime inicio, LocalDateTime fim);
    boolean existsByIdAndDataHoraConsultaBetween(Long id, LocalDateTime inicio, LocalDateTime fim);
    boolean existsByIdAndAtivaTrue(Long id);
}
