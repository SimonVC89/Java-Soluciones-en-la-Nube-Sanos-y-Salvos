package cl.sanosysalvos.reportes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "mascotas")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    private String raza;
    private String color;
    private String especie; // PERRO, GATO, OTRO
    private String tamano;  // PEQUEÑO, MEDIANO, GRANDE
    private String descripcion;

    // Una mascota puede tener muchos reportes (OneToMany)
    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reporte> reportes;

    public Mascota() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public String getTamano() { return tamano; }
    public void setTamano(String tamano) { this.tamano = tamano; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<Reporte> getReportes() { return reportes; }
    public void setReportes(List<Reporte> reportes) { this.reportes = reportes; }
}
