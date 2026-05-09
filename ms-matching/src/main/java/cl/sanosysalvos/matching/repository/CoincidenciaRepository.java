package cl.sanosysalvos.matching.repository;

import cl.sanosysalvos.matching.model.Coincidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CoincidenciaRepository extends JpaRepository<Coincidencia, Long> {
    List<Coincidencia> findByEstado(String estado);
    List<Coincidencia> findByReportePerdidoId(Long reportePerdidoId);
    List<Coincidencia> findByPorcentajeGreaterThanEqual(Double porcentaje);
}
