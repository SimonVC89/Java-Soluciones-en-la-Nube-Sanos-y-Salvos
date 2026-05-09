package cl.sanosysalvos.reportes.controller;

import cl.sanosysalvos.reportes.model.Mascota;
import cl.sanosysalvos.reportes.service.MascotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    // GET /api/mascotas
    @GetMapping
    public ResponseEntity<List<Mascota>> listarTodas() {
        return ResponseEntity.ok(mascotaService.findAll());
    }

    // GET /api/mascotas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Mascota> obtenerPorId(@PathVariable Long id) {
        return mascotaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/mascotas/especie/{especie}
    @GetMapping("/especie/{especie}")
    public ResponseEntity<List<Mascota>> obtenerPorEspecie(@PathVariable String especie) {
        return ResponseEntity.ok(mascotaService.findByEspecie(especie));
    }

    // POST /api/mascotas
    @PostMapping
    public ResponseEntity<Mascota> crear(@Valid @RequestBody Mascota mascota) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mascotaService.save(mascota));
    }

    // PUT /api/mascotas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Mascota> actualizar(@PathVariable Long id,
                                               @Valid @RequestBody Mascota mascota) {
        return mascotaService.update(id, mascota)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/mascotas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return mascotaService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
