package cl.sanosysalvos.geolocalizacion.service;

import cl.sanosysalvos.geolocalizacion.model.Coordenada;
import cl.sanosysalvos.geolocalizacion.model.Zona;
import cl.sanosysalvos.geolocalizacion.repository.CoordenadaRepository;
import cl.sanosysalvos.geolocalizacion.repository.ZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CoordenadaService {

    @Autowired
    private CoordenadaRepository coordenadaRepository;

    @Autowired
    private ZonaRepository zonaRepository;

    public List<Coordenada> findAll() {
        return coordenadaRepository.findAll();
    }

    public Optional<Coordenada> findById(Long id) {
        return coordenadaRepository.findById(id);
    }

    public List<Coordenada> findByZonaId(Long zonaId) {
        return coordenadaRepository.findByZonaId(zonaId);
    }

    public Optional<Coordenada> save(Long zonaId, Coordenada coordenada) {
        return zonaRepository.findById(zonaId).map(zona -> {
            coordenada.setZona(zona);
            return coordenadaRepository.save(coordenada);
        });
    }

    public Optional<Coordenada> update(Long id, Coordenada datos) {
        return coordenadaRepository.findById(id).map(c -> {
            c.setLatitud(datos.getLatitud());
            c.setLongitud(datos.getLongitud());
            c.setDescripcion(datos.getDescripcion());
            return coordenadaRepository.save(c);
        });
    }

    public boolean delete(Long id) {
        if (coordenadaRepository.existsById(id)) {
            coordenadaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
