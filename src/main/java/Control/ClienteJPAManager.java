package Control;

import Entities.Actor;
import Entities.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

public class ClienteJPAManager {
    private static EntityManagerFactory emf;

    public void start() throws IOException {
        try {
            emf = Persistence.createEntityManagerFactory("VideoClubJpaAnnotations");
        } catch (Throwable ex) {
            System.err.println("Failed to create EntityManagerFactory object."
                    + ex);
            throw new ExceptionInInitializerError(ex);
        }
        ClienteJPAManager MA = new ClienteJPAManager();
        FileAccessor fa;
        fa = new FileAccessor();
        fa.readClientesFile("clients.csv");
        System.out.println();
        System.out.println("Clients llegits des del fitxer");
        for (int i = 0; i < fa.listaClientes.size(); i++) {
            System.out.println(fa.listaClientes.get(i).toString());
            MA.addCliente(fa.listaClientes.get(i));
        }
        System.out.println();
        System.out.println("Clients llegits de la base de dades");
        System.out.println();
        MA.listClients();

        //MA.deleteClient(5);
        //MA.updateClient(5, "Antonio");
        System.out.println();
        System.out.println("Clients llegits de la base de dades després de des actualitzacions");
        System.out.println();
        MA.listClients();
    }

    /* Method to CREATE an Actot in the database */
    public void addCliente(Cliente cliente) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        em.close();
    }

    /* Method to READ all Actors */
    public void listClients() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Cliente> result = em.createQuery("from Cliente ", Cliente.class)
                .getResultList();
        for (Cliente cliente : result) {
            System.out.println(cliente.toString());
        }
        em.getTransaction().commit();
        em.close();

    }

    /* Method to UPDATE activity for an actor */
    public void updateClient(Integer ClienteID, String nombre) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Cliente cliente = (Cliente) em.find(Cliente.class, ClienteID);
        cliente.setNombre(nombre);
        em.merge(cliente);
        em.getTransaction().commit();
        em.close();
    }

    /* Method to DELETE an actor from the records */
    public void deleteClient(Integer ClienteID) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Cliente cliente = (Cliente) em.find(Cliente.class, ClienteID);
        em.remove(cliente);
        em.getTransaction().commit();
        em.close();
    }
}
