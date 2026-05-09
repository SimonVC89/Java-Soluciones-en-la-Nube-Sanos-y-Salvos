package cl.sanosysalvos.matching.service;

import cl.sanosysalvos.matching.model.Coincidencia;
import cl.sanosysalvos.matching.repository.CoincidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CoincidenciaService {

    @Autowired
    private CoincidenciaRepository coincidenciaRepository;

    public List<Coincidencia> findAll() {
        return coincidenciaRepository.findAll();
    }

    public Optional<Coincidencia> findById(Long id) {
        return coincidenciaRepository.findById(id);
    }

    public List<Coincidencia> findByEstado(String estado) {
        return coincidenciaRepository.findByEstado(estado);
    }

    public List<Coincidencia> findByAltosPorcentajes() {
        // Retorna coincidencias con porcentaje >= 85%
        return coincidenciaRepository.findByPorcentajeGreaterThanEqual(85.0);
    }

    public Coincidencia save(Coincidencia coincidencia) {
        return coincidenciaRepository.save(coincidencia);
    }

    public Optional<Coincidencia> update(Long id, Coincidencia datos) {
        return coincidenciaRepository.findById(id).map(c -> {
            c.setReportePerdidoId(datos.getReportePerdidoId());
            c.setReporteEncontradoId(datos.getReporteEncontradoId());
            c.setPorcentaje(datos.getPorcentaje());
            c.setEstado(datos.getEstado());
            return coincidenciaRepository.save(c);
        });
    }

    public boolean delete(Long id) {
        if (coincidenciaRepository.existsById(id)) {
            coincidenciaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
