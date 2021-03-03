package Control;

import Entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileAccessor {
    ArrayList<Genero> listaGeneros = new ArrayList<>();
    ArrayList<Actor> actorList = new ArrayList<>();
    ArrayList<Actuacion> listaActuacion = new ArrayList<>();
    ArrayList<Cliente> listaClientes = new ArrayList<>();
    ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
    ArrayList<Visionado> listaVisionados = new ArrayList<>();

    public FileAccessor() {

    }


    public void printActors() {
        for (int i = 0; i < actorList.size(); i++) {
            System.out.println(actorList.get(i).toString());
        }
    }

    public void printActuacion() {
        for (int i = 0; i < listaActuacion.size(); i++) {
            System.out.println(listaActuacion.get(i).toString());
        }
    }

    public void printClientes() {
        for (int i = 0; i < listaClientes.size(); i++) {
            System.out.println(listaClientes.get(i).toString());
        }
    }

    public void printVisionados() {
        for (int i = 0; i < listaVisionados.size(); i++) {
            System.out.println(listaVisionados.get(i).toString());
        }
    }

    public void printListaGeneros() {
        for (int i = 0; i < listaGeneros.size(); i++) {
            System.out.println(listaGeneros.get(i));
        }
    }

    public void printPeliculaList() {
        for (Pelicula pelicula : listaPeliculas) {
            System.out.println(pelicula);
        }
    }

    public void readActorFile(String fichero) throws IOException {
        int codigo;
        String nombre, nacionalidad;

        BufferedReader br = new BufferedReader(new FileReader(fichero));
        String linea = "";
        while ((linea = br.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linea, ",");
            codigo = Integer.parseInt(str.nextToken());
            nombre = str.nextToken();
            nacionalidad = str.nextToken();
            actorList.add(new Actor(codigo, nombre, nacionalidad));
        }
        br.close();
    }

    public void readClientesFile(String fichero) throws IOException {
        String codigo, nombre;

        BufferedReader bReader = new BufferedReader(new FileReader(fichero));
        String linea = "";
        while ((linea = bReader.readLine()) != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(linea, ",");
            codigo = stringTokenizer.nextToken();
            nombre = stringTokenizer.nextToken();

            listaClientes.add(new Cliente(codigo, nombre));
        }
        bReader.close();
    }

    public void readGenerosFile(String filename) throws IOException {
        int id;
        String descripcion;


        BufferedReader br = new BufferedReader(new FileReader(filename));
        String linea = "";
        while ((linea = br.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linea, ",");
            id = Integer.parseInt(str.nextToken());
            descripcion = str.nextToken();
            listaGeneros.add(new Genero(id, descripcion));
        }
        br.close();

    }
    public void readPelicula(String file) throws IOException {
        int id;
        String titulo;
        int idGenero;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea = "";
        // Por cada linea
        while ((linea = br.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linea, ",");
            id = Integer.parseInt(str.nextToken());
            titulo = str.nextToken();
            idGenero = Integer.parseInt(str.nextToken());
            Genero generoP = new Genero();
            for (Genero genero : listaGeneros)  {
                if (genero.getId() == idGenero) {
                    generoP = genero;
                }
            }
            listaPeliculas.add(new Pelicula(id, titulo, generoP));
        }
        br.close();
    }


    public void readActuacionFile(String fichero) throws IOException {
        int codigo_actor, id_pelicula;
        String personaje;

        BufferedReader br = new BufferedReader(new FileReader(fichero));
        String linea = "";

        while ((linea = br.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linea, ",");
            codigo_actor = Integer.parseInt(str.nextToken());
            id_pelicula = Integer.parseInt(str.nextToken());
            personaje = str.nextToken();
            Actor actor = new Actor();
            for (Actor act : actorList) {
                if (act.getCodigo() == codigo_actor) {
                    actor = act;
                }
            }
            Pelicula pelicula = new Pelicula();
            for (Pelicula pel : listaPeliculas) {
                if (pel.getId() == id_pelicula) {
                    pelicula = pel;
                }
            }
            listaActuacion.add(new Actuacion(personaje, actor, pelicula));
        }
    }




    public void readVisionadosFile(String fichero) throws IOException {
        int id_pelicula;
        String codigo_cliente;
        Date fecha_visionado;

        BufferedReader bReader = new BufferedReader(new FileReader(fichero));
        String linea = "";
        while ((linea = bReader.readLine()) != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(linea, ",");
            id_pelicula = Integer.parseInt(stringTokenizer.nextToken());
            codigo_cliente = stringTokenizer.nextToken();
            fecha_visionado = Date.valueOf(stringTokenizer.nextToken());

            Pelicula peliculaTemp = new Pelicula();
            for (Pelicula pelicula : listaPeliculas) {
                if (pelicula.getId() == id_pelicula) {
                    peliculaTemp = pelicula;
                }
            }

            Cliente clienteTemp = new Cliente();
            for (Cliente cliente : listaClientes) {
                if (cliente.getCodigo().equals(codigo_cliente)) {
                    clienteTemp = cliente;
                }
            }

            listaVisionados.add(new Visionado(peliculaTemp, clienteTemp, fecha_visionado));
        }
        bReader.close();
    }




}
