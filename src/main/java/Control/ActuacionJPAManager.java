package Control;

import Entities.Actor;
import Entities.Actuacion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

public class ActuacionJPAManager {
    private static EntityManagerFactory emf;

    public void start() throws IOException {
        try {
            emf = Persistence.createEntityManagerFactory("VideoClubJpaAnnotations");
        } catch (Throwable ex) {
            System.err.println("Failed to create EntityManagerFactory object."
                    + ex);
            throw new ExceptionInInitializerError(ex);
        }
        ActuacionJPAManager MA = new ActuacionJPAManager();
        FileAccessor fa;
        fa = new FileAccessor();
        fa.readActorFile("actor.csv");
       fa.readPelicula("peliculas.csv");
        fa.readActuacionFile("actuacion.csv");
        System.out.println("Actuacions llegides des del fitxer");
        for (int i = 0; i < fa.listaActuacion.size(); i++) {
            System.out.println(fa.listaActuacion.get(i).toString());
            MA.addActuacion(fa.listaActuacion.get(i));
        }
        System.out.println();
        System.out.println("Actuacions llegides de la base de dades");
        System.out.println();
        MA.listActuacion();
        System.out.println();
        //MA.deleteActuacion(5);
//         MA.updateActuacion(5, "Antonio");
        System.out.println("Actuacions llegides de la base de dades després de des actualitzacions");
        System.out.println();
        MA.listActuacion();
        System.out.println();
    }

    /* Method to CREATE an Actot in the database */
    public void addActuacion(Actuacion actuacion) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(actuacion);
        em.getTransaction().commit();
        em.close();
    }

    /* Method to READ all Actors */
    public void listActuacion() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Actuacion> result = em.createQuery("from Actuacion", Actuacion.class)
                .getResultList();
        for (Actuacion actuacion : result) {
            System.out.println(actuacion.toString());
        }
        em.getTransaction().commit();
        em.close();

    }

    /* Method to UPDATE activity for an actor */
    public void updateActuacion(Integer ActuacionID, String Personajenombre) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Actuacion actuacion = (Actuacion) em.find(Actuacion.class, ActuacionID);
        actuacion.setPersonaje(Personajenombre);
        em.merge(actuacion);
        em.getTransaction().commit();
        em.close();
    }

    /* Method to DELETE an actor from the records */
    public void deleteActuacion(Integer ActuacionID) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Actuacion actuacion = (Actuacion) em.find(Actuacion.class, ActuacionID);
        em.remove(actuacion);
        em.getTransaction().commit();
        em.close();
    }
}
