package cl.sanosysalvos.geolocalizacion.repository;

import cl.sanosysalvos.geolocalizacion.model.Coordenada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CoordenadaRepository extends JpaRepository<Coordenada, Long> {
    List<Coordenada> findByZonaId(Long zonaId);
}
