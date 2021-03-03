package Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "watched")
public class Visionado implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_film")
    private Pelicula pelicula;

    @Transient
    private int id_pelicula;

    @Id
    @ManyToOne
    @JoinColumn(name = "code_client")
    private Cliente cliente;

    @Transient
    private String codigo_cliente;

    @Column(name =  "in_date")
    private Date fecha;

    public Visionado(Pelicula pelicula, Cliente cliente, Date fecha) {
        super();
        this.pelicula = pelicula;
        this.id_pelicula = pelicula.getId();
        this.cliente = cliente;
        this.codigo_cliente = cliente.getCodigo();
        this.fecha = fecha;
    }

    public Visionado() {

    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public int getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(int id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(String codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Visionado [id_pelicula=" + id_pelicula + ", codigo_cliente=" + codigo_cliente + ", fecha=" + fecha
                + "]";
    }

}