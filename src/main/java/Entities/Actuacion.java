package Entities;

import Entities.Actor;
import Entities.Pelicula;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "plays")
public class Actuacion implements Serializable {

    @Column(name =  "character")
    String personaje;

    @Id
    @ManyToOne()
    @JoinColumn(name = "code_actor")
    private Actor actor;

    @Id
    @ManyToOne()
    @JoinColumn(name = "id_film")
    private Pelicula pelicula;




    public Actuacion(String personaje, Actor actor, Pelicula pelicula) {
        this.personaje = personaje;
        this.actor = actor;
        this.pelicula = pelicula;
    }

    public Actuacion(){

    }

    public String getPersonaje() {
        return personaje;
    }

    public void setPersonaje(String personaje) {
        this.personaje = personaje;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    @Override
    public String toString() {
        return "Actuacion{" +
                ", character='" + personaje + '\'' +
                ", actor=" + actor.getCodigo() +
                ", pelicula=" + pelicula.getId() +
                '}';
    }
}