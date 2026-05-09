package cl.sanosysalvos.usuarios.controller;

import cl.sanosysalvos.usuarios.model.Usuario;
import cl.sanosysalvos.usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // GET /api/usuarios — Listar todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    // GET /api/usuarios/{id} — Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        return usuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/usuarios/email/{email} — Buscar por email
    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> obtenerPorEmail(@PathVariable String email) {
        return usuarioService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/usuarios — Crear nuevo usuario
    @PostMapping
    public ResponseEntity<Usuario> crear(@Valid @RequestBody Usuario usuario) {
        Usuario creado = usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // PUT /api/usuarios/{id} — Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id,
                                               @Valid @RequestBody Usuario usuario) {
        return usuarioService.update(id, usuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/usuarios/{id} — Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (usuarioService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
