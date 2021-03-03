package Control;

import Entities.Genero;
import Entities.Pelicula;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

public class GeneroJPAManager {
    private static EntityManagerFactory emf;

    public void start() throws IOException {
        try {
            emf = Persistence.createEntityManagerFactory("VideoClubJpaAnnotations");
        } catch (Throwable ex) {
            System.err.println("Failed to create EntityManagerFactory object."
                    + ex);
            throw new ExceptionInInitializerError(ex);
        }
        GeneroJPAManager MA = new GeneroJPAManager();
        FileAccessor fa;
        fa = new FileAccessor();
        fa.readGenerosFile("generos.csv");
        System.out.println("Generes llegits des del fitxer");
        for (int i = 0; i < fa.listaGeneros.size(); i++) {
            System.out.println(fa.listaGeneros.get(i).toString());
            MA.addGenero(fa.listaGeneros.get(i));
        }
        System.out.println();
        System.out.println("Generes llegits de la base de dades");
        System.out.println();
        MA.llistGeneres();
        System.out.println();
        //MA.deleteGenere(5);
        //MA.updateGenere(5, "Acció");
        System.out.println("Generes llegits de la base de dades després de des actualitzacions");
        System.out.println();
        MA.llistGeneres();
        System.out.println();
    }

    /* Method to CREATE an Actot in the database */
    public void addGenero(Genero genero) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(genero);
        em.getTransaction().commit();
        em.close();
    }

    /* Method to READ all Actors */
    public void llistGeneres() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Genero> result = em.createQuery("from Genero ", Genero.class)
                .getResultList();
        for (Genero genero : result) {
            System.out.println(genero.toString());
        }
        em.getTransaction().commit();
        em.close();

    }

    /* Method to UPDATE activity for an actor */
    public void updateGenere(Integer genereID, String nomGenere) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Genero genero = (Genero) em.find(Genero.class, genereID);
        genero.setDescripcion(nomGenere);
        em.merge(genero);
        em.getTransaction().commit();
        em.close();
    }

    /* Method to DELETE an actor from the records */
    public void deleteGenere(Integer genreID) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Genero genero = (Genero) em.find(Genero.class, genreID);
        em.remove(genero);
        em.getTransaction().commit();
        em.close();
    }
}
