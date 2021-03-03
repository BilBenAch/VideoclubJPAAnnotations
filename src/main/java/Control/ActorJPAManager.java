package Control;

import Entities.Actor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

public class ActorJPAManager {
    private static EntityManagerFactory emf;

    public void start() throws IOException {
        try {
            emf = Persistence.createEntityManagerFactory("VideoClubJpaAnnotations");
        } catch (Throwable ex) {
            System.err.println("Failed to create EntityManagerFactory object."
                    + ex);
            throw new ExceptionInInitializerError(ex);
        }
        ActorJPAManager MA = new ActorJPAManager();
        FileAccessor fa;
        fa = new FileAccessor();
        fa.readActorFile("actor.csv");
        System.out.println("Actors llegits des del fitxer");
        for (int i = 0; i < fa.actorList.size(); i++) {
            System.out.println(fa.actorList.get(i).toString());
            MA.addActor(fa.actorList.get(i));
        }
        System.out.println();
        System.out.println("Actors llegits de la base de dades");
        System.out.println();
        MA.listAutors();
        System.out.println();
        //MA.deleteActor(5);
       // MA.updateActor(5, "Antonio");
        System.out.println("Actors llegits de la base de dades després de des actualitzacions");
        System.out.println();
        MA.listAutors();
        System.out.println();
    }

    /* Method to CREATE an Actot in the database */
    public void addActor(Actor actor) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(actor);
        em.getTransaction().commit();
        em.close();
    }

    /* Method to READ all Actors */
    public void listAutors() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Actor> result = em.createQuery("from Actor", Actor.class)
                .getResultList();
        for (Actor actor : result) {
            System.out.println(actor.toString());
        }
        em.getTransaction().commit();
        em.close();

    }

    /* Method to UPDATE activity for an actor */
    public void updateActor(Integer ActorID, String nombre) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Actor actor = (Actor) em.find(Actor.class, ActorID);
        actor.setNombre(nombre);
        em.merge(actor);
        em.getTransaction().commit();
        em.close();
    }

    /* Method to DELETE an actor from the records */
    public void deleteActor(Integer ActorId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Actor actor = (Actor) em.find(Actor.class, ActorId);
        em.remove(actor);
        em.getTransaction().commit();
        em.close();
    }
}
