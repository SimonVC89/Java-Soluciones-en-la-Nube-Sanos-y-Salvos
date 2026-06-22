package cl.sanosysalvos.reportes.service;

import cl.sanosysalvos.reportes.model.Reporte;
import cl.sanosysalvos.reportes.repository.ReporteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private SqsService sqsService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Reporte> findAll() {
        return reporteRepository.findAll();
    }

    public Optional<Reporte> findById(Long id) {
        return reporteRepository.findById(id);
    }

    public List<Reporte> findByTipo(String tipo) {
        return reporteRepository.findByTipo(tipo);
    }

    public List<Reporte> findByEstado(String estado) {
        return reporteRepository.findByEstado(estado);
    }

    public Reporte save(Reporte reporte) {
        Reporte guardado = reporteRepository.save(reporte);
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("reporteId",     guardado.getId() != null ? guardado.getId() : 0L);
            payload.put("tipoReporte",   guardado.getTipo() != null ? guardado.getTipo() : "DESCONOCIDO");
            payload.put("nombreMascota", guardado.getMascota() != null ? guardado.getMascota().getNombre() : "Desconocida");
            payload.put("usuarioId",     guardado.getUsuarioId() != null ? guardado.getUsuarioId() : 1L);
            payload.put("descripcion",   guardado.getDescripcion() != null ? guardado.getDescripcion() : "");
            String mensaje = objectMapper.writeValueAsString(payload);
            sqsService.enviarMensaje(mensaje);
        } catch (Exception e) {
            System.out.println("[SQS] Error al enviar mensaje SQS: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        return guardado;
    }

    public Optional<Reporte> update(Long id, Reporte datos) {
        return reporteRepository.findById(id).map(r -> {
            r.setTipo(datos.getTipo());
            r.setDescripcion(datos.getDescripcion());
            r.setLatitud(datos.getLatitud());
            r.setLongitud(datos.getLongitud());
            r.setEstado(datos.getEstado());
            return reporteRepository.save(r);
        });
    }

    public boolean delete(Long id) {
        if (reporteRepository.existsById(id)) {
            reporteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
