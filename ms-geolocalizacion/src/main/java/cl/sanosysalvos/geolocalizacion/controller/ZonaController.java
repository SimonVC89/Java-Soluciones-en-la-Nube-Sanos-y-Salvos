package cl.sanosysalvos.geolocalizacion.controller;

import cl.sanosysalvos.geolocalizacion.model.Zona;
import cl.sanosysalvos.geolocalizacion.service.ZonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/zonas")
public class ZonaController {

    @Autowired
    private ZonaService zonaService;

    // GET /api/zonas
    @GetMapping
    public ResponseEntity<List<Zona>> listarTodas() {
        return ResponseEntity.ok(zonaService.findAll());
    }

    // GET /api/zonas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Zona> obtenerPorId(@PathVariable Long id) {
        return zonaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/zonas/region/{region}
    @GetMapping("/region/{region}")
    public ResponseEntity<List<Zona>> obtenerPorRegion(@PathVariable String region) {
        return ResponseEntity.ok(zonaService.findByRegion(region));
    }

    // POST /api/zonas
    @PostMapping
    public ResponseEntity<Zona> crear(@Valid @RequestBody Zona zona) {
        return ResponseEntity.status(HttpStatus.CREATED).body(zonaService.save(zona));
    }

    // PUT /api/zonas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Zona> actualizar(@PathVariable Long id,
                                            @Valid @RequestBody Zona zona) {
        return zonaService.update(id, zona)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/zonas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return zonaService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
