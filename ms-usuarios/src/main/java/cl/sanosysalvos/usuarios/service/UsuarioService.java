package cl.sanosysalvos.usuarios.service;

import cl.sanosysalvos.usuarios.model.Usuario;
import cl.sanosysalvos.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> update(Long id, Usuario datos) {
        return usuarioRepository.findById(id).map(u -> {
            u.setNombre(datos.getNombre());
            u.setEmail(datos.getEmail());
            u.setTelefono(datos.getTelefono());
            u.setActivo(datos.getActivo());
            return usuarioRepository.save(u);
        });
    }

    public boolean delete(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
