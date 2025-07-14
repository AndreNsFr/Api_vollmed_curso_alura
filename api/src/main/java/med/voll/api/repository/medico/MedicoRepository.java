package med.voll.api.repository.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query(value = "SELECT * FROM medicos WHERE ativo = true ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Medico findAnyMedico();

    boolean existsByIdAndAtivoTrue(Long id);

}
