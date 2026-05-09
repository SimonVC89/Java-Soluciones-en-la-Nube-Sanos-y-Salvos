package cl.sanosysalvos.geolocalizacion.controller;

import cl.sanosysalvos.geolocalizacion.model.Coordenada;
import cl.sanosysalvos.geolocalizacion.service.CoordenadaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/coordenadas")
public class CoordenadaController {

    @Autowired
    private CoordenadaService coordenadaService;

    // GET /api/coordenadas
    @GetMapping
    public ResponseEntity<List<Coordenada>> listarTodas() {
        return ResponseEntity.ok(coordenadaService.findAll());
    }

    // GET /api/coordenadas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Coordenada> obtenerPorId(@PathVariable Long id) {
        return coordenadaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/coordenadas/zona/{zonaId}
    @GetMapping("/zona/{zonaId}")
    public ResponseEntity<List<Coordenada>> obtenerPorZona(@PathVariable Long zonaId) {
        return ResponseEntity.ok(coordenadaService.findByZonaId(zonaId));
    }

    // POST /api/coordenadas/zona/{zonaId}
    @PostMapping("/zona/{zonaId}")
    public ResponseEntity<Coordenada> crear(@PathVariable Long zonaId,
                                             @Valid @RequestBody Coordenada coordenada) {
        return coordenadaService.save(zonaId, coordenada)
                .map(c -> ResponseEntity.status(HttpStatus.CREATED).body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /api/coordenadas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Coordenada> actualizar(@PathVariable Long id,
                                                  @Valid @RequestBody Coordenada coordenada) {
        return coordenadaService.update(id, coordenada)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/coordenadas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return coordenadaService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
