import Control.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ActorJPAManager actorJPAManager = new ActorJPAManager();
        ClienteJPAManager clienteJPAManager = new ClienteJPAManager();
        ActuacionJPAManager actuacionJPAManager = new ActuacionJPAManager();
        PeliculaJPAManager peliculaJPAManager = new PeliculaJPAManager();
        GeneroJPAManager generoJPAManager = new GeneroJPAManager();
        VisionadoJPAManager visionadoJPAManager = new VisionadoJPAManager();

//        actorJPAManager.start();
//        clienteJPAManager.start();
//        generoJPAManager.start();
//        peliculaJPAManager.start();
//        actuacionJPAManager.start();
        visionadoJPAManager.start();
    }
}
