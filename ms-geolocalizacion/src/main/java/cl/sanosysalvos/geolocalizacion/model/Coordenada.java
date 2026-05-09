package cl.sanosysalvos.geolocalizacion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "coordenadas")
public class Coordenada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double latitud;

    @Column(nullable = false)
    private Double longitud;

    private String descripcion;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    // Muchas coordenadas pertenecen a una zona (ManyToOne)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "zona_id", nullable = false)
    @JsonIgnoreProperties("coordenadas")
    private Zona zona;

    public Coordenada() {}

    @PrePersist
    protected void onCreate() {
        this.fechaRegistro = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getLatitud() { return latitud; }
    public void setLatitud(Double latitud) { this.latitud = latitud; }

    public Double getLongitud() { return longitud; }
    public void setLongitud(Double longitud) { this.longitud = longitud; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public Zona getZona() { return zona; }
    public void setZona(Zona zona) { this.zona = zona; }
}
