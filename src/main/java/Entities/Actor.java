package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "actor")
public class Actor implements Serializable {
    @Id
    @Column(name = "code")
    private int codigo;

    @Column(name = "name")
    private String nombre;

    @Column(name = "nationality")
    private String nacionalidad;

    public Actor(int codigo, String nombre, String nacionalidad) {
        super();
        this.codigo = codigo;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public Actor() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "code=" + codigo +
                ", name='" + nombre + '\'' +
                ", nacionality='" + nacionalidad + '\'' +
                '}';
    }
}