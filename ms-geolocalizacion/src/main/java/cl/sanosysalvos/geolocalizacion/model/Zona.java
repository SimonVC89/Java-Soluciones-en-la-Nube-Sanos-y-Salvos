package cl.sanosysalvos.geolocalizacion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "zonas")
public class Zona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la zona es obligatorio")
    @Column(nullable = false)
    private String nombre;

    private String descripcion;
    private String region;

    // Una zona tiene muchas coordenadas (OneToMany)
    @OneToMany(mappedBy = "zona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Coordenada> coordenadas;

    public Zona() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public List<Coordenada> getCoordenadas() { return coordenadas; }
    public void setCoordenadas(List<Coordenada> coordenadas) { this.coordenadas = coordenadas; }
}
