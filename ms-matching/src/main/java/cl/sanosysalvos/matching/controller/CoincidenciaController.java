package cl.sanosysalvos.matching.controller;

import cl.sanosysalvos.matching.model.Coincidencia;
import cl.sanosysalvos.matching.service.CoincidenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/coincidencias")
public class CoincidenciaController {

    @Autowired
    private CoincidenciaService coincidenciaService;

    // GET /api/coincidencias
    @GetMapping
    public ResponseEntity<List<Coincidencia>> listarTodas() {
        return ResponseEntity.ok(coincidenciaService.findAll());
    }

    // GET /api/coincidencias/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Coincidencia> obtenerPorId(@PathVariable Long id) {
        return coincidenciaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/coincidencias/estado/{estado}
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Coincidencia>> obtenerPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(coincidenciaService.findByEstado(estado));
    }

    // GET /api/coincidencias/altas — Coincidencias >= 85%
    @GetMapping("/altas")
    public ResponseEntity<List<Coincidencia>> obtenerAltas() {
        return ResponseEntity.ok(coincidenciaService.findByAltosPorcentajes());
    }

    // POST /api/coincidencias
    @PostMapping
    public ResponseEntity<Coincidencia> crear(@Valid @RequestBody Coincidencia coincidencia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(coincidenciaService.save(coincidencia));
    }

    // PUT /api/coincidencias/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Coincidencia> actualizar(@PathVariable Long id,
                                                    @Valid @RequestBody Coincidencia coincidencia) {
        return coincidenciaService.update(id, coincidencia)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/coincidencias/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return coincidenciaService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
