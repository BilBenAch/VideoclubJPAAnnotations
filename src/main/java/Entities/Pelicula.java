package Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "film")
public class Pelicula implements Serializable {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String titulo;

    @OneToOne
    @JoinColumn(name = "id_genre")
    private Genero genero;


    public Pelicula(int id, String titulo, Genero genero) {
        super();
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
    }

    public Pelicula() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", title='" + titulo + '\'' +
                ", genero=" + genero +
                '}';
    }
}
