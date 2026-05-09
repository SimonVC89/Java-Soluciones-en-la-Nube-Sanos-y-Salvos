package cl.sanosysalvos.reportes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "reportes")
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // PERDIDO o ENCONTRADO
    @NotBlank(message = "El tipo de reporte es obligatorio")
    @Column(nullable = false)
    private String tipo;

    private String descripcion;

    @Column(name = "fecha_reporte")
    private LocalDateTime fechaReporte;

    private Double latitud;
    private Double longitud;

    // ACTIVO, RESUELTO, CERRADO
    private String estado;

    @Column(name = "usuario_id")
    private Long usuarioId;

    // Muchos reportes pertenecen a una mascota (ManyToOne)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mascota_id", nullable = false)
    @JsonIgnoreProperties("reportes")
    private Mascota mascota;

    public Reporte() {}

    @PrePersist
    protected void onCreate() {
        this.fechaReporte = LocalDateTime.now();
        this.estado = "ACTIVO";
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDateTime getFechaReporte() { return fechaReporte; }
    public void setFechaReporte(LocalDateTime fechaReporte) { this.fechaReporte = fechaReporte; }

    public Double getLatitud() { return latitud; }
    public void setLatitud(Double latitud) { this.latitud = latitud; }

    public Double getLongitud() { return longitud; }
    public void setLongitud(Double longitud) { this.longitud = longitud; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Mascota getMascota() { return mascota; }
    public void setMascota(Mascota mascota) { this.mascota = mascota; }
}
