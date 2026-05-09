package cl.sanosysalvos.matching.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "coincidencias")
public class Coincidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reporte_perdido_id", nullable = false)
    private Long reportePerdidoId;

    @Column(name = "reporte_encontrado_id", nullable = false)
    private Long reporteEncontradoId;

    // Porcentaje de similitud entre 0.0 y 100.0
    private Double porcentaje;

    // PENDIENTE, CONFIRMADA, DESCARTADA
    private String estado;

    @Column(name = "fecha_coincidencia")
    private LocalDateTime fechaCoincidencia;

    public Coincidencia() {}

    @PrePersist
    protected void onCreate() {
        this.fechaCoincidencia = LocalDateTime.now();
        this.estado = "PENDIENTE";
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getReportePerdidoId() { return reportePerdidoId; }
    public void setReportePerdidoId(Long reportePerdidoId) { this.reportePerdidoId = reportePerdidoId; }

    public Long getReporteEncontradoId() { return reporteEncontradoId; }
    public void setReporteEncontradoId(Long reporteEncontradoId) { this.reporteEncontradoId = reporteEncontradoId; }

    public Double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(Double porcentaje) { this.porcentaje = porcentaje; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDateTime getFechaCoincidencia() { return fechaCoincidencia; }
    public void setFechaCoincidencia(LocalDateTime fechaCoincidencia) { this.fechaCoincidencia = fechaCoincidencia; }
}
