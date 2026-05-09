package cl.sanosysalvos.notificaciones.controller;

import cl.sanosysalvos.notificaciones.model.Notificacion;
import cl.sanosysalvos.notificaciones.service.NotificacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    // GET /api/notificaciones
    @GetMapping
    public ResponseEntity<List<Notificacion>> listarTodas() {
        return ResponseEntity.ok(notificacionService.findAll());
    }

    // GET /api/notificaciones/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> obtenerPorId(@PathVariable Long id) {
        return notificacionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/notificaciones/usuario/{usuarioId}
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Notificacion>> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(notificacionService.findByUsuarioId(usuarioId));
    }

    // GET /api/notificaciones/no-leidas
    @GetMapping("/no-leidas")
    public ResponseEntity<List<Notificacion>> obtenerNoLeidas() {
        return ResponseEntity.ok(notificacionService.findNoLeidas());
    }

    // POST /api/notificaciones
    @PostMapping
    public ResponseEntity<Notificacion> crear(@Valid @RequestBody Notificacion notificacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notificacionService.save(notificacion));
    }

    // PATCH /api/notificaciones/{id}/leer — Marcar como leída
    @PatchMapping("/{id}/leer")
    public ResponseEntity<Notificacion> marcarComoLeida(@PathVariable Long id) {
        return notificacionService.marcarComoLeida(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /api/notificaciones/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Notificacion> actualizar(@PathVariable Long id,
                                                    @Valid @RequestBody Notificacion notificacion) {
        return notificacionService.update(id, notificacion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/notificaciones/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return notificacionService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
