package cl.sanosysalvos.reportes.controller;

import cl.sanosysalvos.reportes.model.Reporte;
import cl.sanosysalvos.reportes.service.ReporteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    // GET /api/reportes
    @GetMapping
    public ResponseEntity<List<Reporte>> listarTodos() {
        return ResponseEntity.ok(reporteService.findAll());
    }

    // GET /api/reportes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Reporte> obtenerPorId(@PathVariable Long id) {
        return reporteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/reportes/tipo/{tipo} — PERDIDO o ENCONTRADO
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Reporte>> obtenerPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(reporteService.findByTipo(tipo));
    }

    // GET /api/reportes/estado/{estado} — ACTIVO, RESUELTO, CERRADO
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Reporte>> obtenerPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(reporteService.findByEstado(estado));
    }

    // POST /api/reportes
    @PostMapping
    public ResponseEntity<Reporte> crear(@Valid @RequestBody Reporte reporte) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reporteService.save(reporte));
    }

    // PUT /api/reportes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Reporte> actualizar(@PathVariable Long id,
                                               @Valid @RequestBody Reporte reporte) {
        return reporteService.update(id, reporte)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/reportes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return reporteService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
