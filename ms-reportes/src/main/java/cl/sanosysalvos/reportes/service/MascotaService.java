package cl.sanosysalvos.reportes.service;

import cl.sanosysalvos.reportes.model.Mascota;
import cl.sanosysalvos.reportes.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<Mascota> findAll() {
        return mascotaRepository.findAll();
    }

    public Optional<Mascota> findById(Long id) {
        return mascotaRepository.findById(id);
    }

    public List<Mascota> findByEspecie(String especie) {
        return mascotaRepository.findByEspecie(especie);
    }

    public Mascota save(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public Optional<Mascota> update(Long id, Mascota datos) {
        return mascotaRepository.findById(id).map(m -> {
            m.setNombre(datos.getNombre());
            m.setRaza(datos.getRaza());
            m.setColor(datos.getColor());
            m.setEspecie(datos.getEspecie());
            m.setTamano(datos.getTamano());
            m.setDescripcion(datos.getDescripcion());
            return mascotaRepository.save(m);
        });
    }

    public boolean delete(Long id) {
        if (mascotaRepository.existsById(id)) {
            mascotaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
