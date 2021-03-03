package Control;

import Entities.Actuacion;
import Entities.Visionado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class VisionadoJPAManager {
    private static EntityManagerFactory emf;

    public void start() throws IOException {
        try {
            emf = Persistence.createEntityManagerFactory("VideoClubJpaAnnotations");
        } catch (Throwable ex) {
            System.err.println("Failed to create EntityManagerFactory object."
                    + ex);
            throw new ExceptionInInitializerError(ex);
        }
        VisionadoJPAManager MA = new VisionadoJPAManager();
        FileAccessor fa;
        fa = new FileAccessor();
        fa.readPelicula("peliculas.csv");
        fa.readClientesFile("clients.csv");
        fa.readVisionadosFile("visionats.csv");
        System.out.println("Visionats llegits des del fitxer");
        for (int i = 0; i < fa.listaVisionados.size(); i++) {
            System.out.println(fa.listaVisionados.get(i).toString());
            MA.addVisionado(fa.listaVisionados.get(i));
        }
        System.out.println();
        System.out.println("Visionats llegits de la base de dades");
        System.out.println();
        MA.listaVisionats();
        System.out.println();
        //MA.deleteVisionado(5);
      //  MA.updateVisionado(5, "2021-02-17");
        System.out.println("Visionats llegits de la base de dades després de des actualitzacions");
        System.out.println();
        MA.listaVisionats();
        System.out.println();
    }

    /* Method to CREATE an Actot in the database */
    public void addVisionado(Visionado visionado) {
       // PeliculaClientePK peliculaClientePK = new PeliculaClientePK();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(visionado);
        em.getTransaction().commit();
        em.close();
    }

    /* Method to READ all Actors */
    public void listaVisionats() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Visionado> result = em.createQuery("from Visionado ", Visionado.class)
                .getResultList();
        for (Visionado visionado : result) {
            System.out.println(visionado.toString());
        }
        em.getTransaction().commit();
        em.close();

    }

    /* Method to UPDATE activity for an actor */
    public void updateVisionado(Integer visionadoID, String nuevaFechaVisionado) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Visionado visionado = (Visionado) em.find(Visionado.class, visionadoID);
        visionado.setFecha(Date.valueOf(nuevaFechaVisionado));
        em.merge(visionado);
        em.getTransaction().commit();
        em.close();
    }

    /* Method to DELETE an actor from the records */
    public void deleteVisionado(Integer visionadoID) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Visionado visionado = (Visionado) em.find(Visionado.class, visionadoID);
        em.remove(visionado);
        em.getTransaction().commit();
        em.close();
    }
}
