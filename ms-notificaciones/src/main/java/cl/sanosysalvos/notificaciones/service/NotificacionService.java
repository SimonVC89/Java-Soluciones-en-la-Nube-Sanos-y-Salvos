package cl.sanosysalvos.notificaciones.service;

import cl.sanosysalvos.notificaciones.model.Notificacion;
import cl.sanosysalvos.notificaciones.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Notificacion> findAll() {
        return notificacionRepository.findAll();
    }

    public Optional<Notificacion> findById(Long id) {
        return notificacionRepository.findById(id);
    }

    public List<Notificacion> findByUsuarioId(Long usuarioId) {
        return notificacionRepository.findByUsuarioId(usuarioId);
    }

    public List<Notificacion> findNoLeidas() {
        return notificacionRepository.findByLeida(false);
    }

    public Notificacion save(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public Optional<Notificacion> marcarComoLeida(Long id) {
        return notificacionRepository.findById(id).map(n -> {
            n.setLeida(true);
            return notificacionRepository.save(n);
        });
    }

    public Optional<Notificacion> update(Long id, Notificacion datos) {
        return notificacionRepository.findById(id).map(n -> {
            n.setMensaje(datos.getMensaje());
            n.setTipo(datos.getTipo());
            n.setLeida(datos.getLeida());
            n.setReporteId(datos.getReporteId());
            return notificacionRepository.save(n);
        });
    }

    public boolean delete(Long id) {
        if (notificacionRepository.existsById(id)) {
            notificacionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
