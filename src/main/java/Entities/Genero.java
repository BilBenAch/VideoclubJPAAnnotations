package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "genre")
public class Genero implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String descripcion;

    public Genero(int id, String descripcion) {
        super();
        this.id = id;
        this.descripcion = descripcion;
    }

    public Genero() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Genero{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}

