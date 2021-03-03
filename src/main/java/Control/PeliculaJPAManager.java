package Control;

import Entities.Actuacion;
import Entities.Pelicula;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

public class PeliculaJPAManager {
    private static EntityManagerFactory emf;

    public void start() throws IOException {
        try {
            emf = Persistence.createEntityManagerFactory("VideoClubJpaAnnotations");
        } catch (Throwable ex) {
            System.err.println("Failed to create EntityManagerFactory object."
                    + ex);
            throw new ExceptionInInitializerError(ex);
        }
        PeliculaJPAManager MA = new PeliculaJPAManager();
        FileAccessor fa;
        fa = new FileAccessor();
        fa.readGenerosFile("generos.csv");
        fa.readPelicula("peliculas.csv");
        System.out.println("Pelicules llegides des del fitxer");
        for (int i = 0; i < fa.listaPeliculas.size(); i++) {
            System.out.println(fa.listaPeliculas.get(i).toString());
            MA.addPeliculas(fa.listaPeliculas.get(i));
        }
        System.out.println();
        System.out.println("Pelicules llegides de la base de dades");
        System.out.println();
        MA.listaPeliculas();
        System.out.println();
        //MA.deletePelicula(5);
//         MA.updatePelicula(5, "Antonio");
        System.out.println("Pelicules llegides de la base de dades després de des actualitzacions");
        System.out.println();
        MA.listaPeliculas();
        System.out.println();
    }

    /* Method to CREATE an Actot in the database */
    public void addPeliculas(Pelicula pelicula) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(pelicula);
        em.getTransaction().commit();
        em.close();
    }

    /* Method to READ all Actors */
    public void listaPeliculas() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Pelicula> result = em.createQuery("from Pelicula ", Pelicula.class)
                .getResultList();
        for (Pelicula pelicula : result) {
            System.out.println(pelicula.toString());
        }
        em.getTransaction().commit();
        em.close();

    }

    /* Method to UPDATE activity for an actor */
    public void updatePelicula(Integer peliculaID, String peliculaTitulo) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Pelicula pelicula = (Pelicula) em.find(Pelicula.class, peliculaID);
        pelicula.setTitulo(peliculaTitulo);
        em.merge(pelicula);
        em.getTransaction().commit();
        em.close();
    }

    /* Method to DELETE an actor from the records */
    public void deletePelicula(Integer peliculaID) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Pelicula pelicula = (Pelicula) em.find(Pelicula.class, peliculaID);
        em.remove(pelicula);
        em.getTransaction().commit();
        em.close();
    }
}
