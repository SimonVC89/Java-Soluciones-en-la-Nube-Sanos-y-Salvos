package cl.sanosysalvos.geolocalizacion.service;

import cl.sanosysalvos.geolocalizacion.model.Zona;
import cl.sanosysalvos.geolocalizacion.repository.ZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ZonaService {

    @Autowired
    private ZonaRepository zonaRepository;

    public List<Zona> findAll() {
        return zonaRepository.findAll();
    }

    public Optional<Zona> findById(Long id) {
        return zonaRepository.findById(id);
    }

    public List<Zona> findByRegion(String region) {
        return zonaRepository.findByRegion(region);
    }

    public Zona save(Zona zona) {
        return zonaRepository.save(zona);
    }

    public Optional<Zona> update(Long id, Zona datos) {
        return zonaRepository.findById(id).map(z -> {
            z.setNombre(datos.getNombre());
            z.setDescripcion(datos.getDescripcion());
            z.setRegion(datos.getRegion());
            return zonaRepository.save(z);
        });
    }

    public boolean delete(Long id) {
        if (zonaRepository.existsById(id)) {
            zonaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
