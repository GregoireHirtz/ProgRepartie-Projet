package serveur;

import interfaces.ServiceCalcul;
import interfaces.ServiceRayTracing;
import raytracer.Disp;
import raytracer.Image;
import raytracer.Scene;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class ServeurCentral extends RemoteServer implements ServiceRayTracing {

    private ArrayList<ServiceCalcul> calculateurs = new ArrayList<ServiceCalcul>();

    /**
     * methode qui permet de demande au serveur central le calcul de la scene fournie
     * @param scene
     * @return
     */
    @Override
    public Image calculerScene(Scene scene, int largeur, int hauteur) throws RemoteException {
        try {
            // Notification de début
            String clientHost = getClientHost();
            System.out.println("\u001B[37m" + clientHost + ": Lancement d'un calcul \u001B[36m" + largeur + "x" + hauteur + "\u001B[0m");

            // Création du calcul
            CalculScene calculScene = new CalculScene(scene, hauteur, largeur);
            Image image = new Image(largeur, hauteur);

            // Calcul
            Instant debut = Instant.now();
            image = continuerCalcul(calculScene, image);
            Instant fin = Instant.now();
            long duree = Duration.between(debut, fin).toMillis();

            // Notification de fin
            System.out.print(clientHost + ": Calcul \u001B[36m" + largeur + "x" + hauteur + "\u001B[0m");
            System.out.println("terminer en \u001B[36m" + duree  +"\u001B[0m" + " ms");
            return image;

        }catch (ServerNotActiveException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Image continuerCalcul(CalculScene calculScene, Image image) {
        ArrayList<Thread> threads = new ArrayList<>();

        // Attend qu'il y ait un service de calcul disponible
        while (calculateurs.size() == 0) {}

        ArrayList<ServiceCalcul> calculateurs_clone = (ArrayList<ServiceCalcul>)calculateurs.clone();
        for(ServiceCalcul serviceCalcul : calculateurs_clone) {
            Thread thread = new Thread(() -> {
                Calcul calcul = null;
                try{
                    while (true) {
                        calcul = calculScene.getCalcul();
                        Image temp_image = serviceCalcul.effectuerCalcul(calcul.scene, calcul.x, calcul.y, calcul.w, calcul.h);
                        // TODO -> Ajouter l'image au resultat
                    }
                }
                catch(RemoteException e) {
                    // Quand le client de calcul s'en va son calcul n'a pas été terminer,
                    // On le remet donc dans la liste
                    if(calcul != null) {
                        calculScene.ajouterCalcul(calcul);
                    }
                    retirerServiceCalcul(serviceCalcul);
                }
                catch(IndexOutOfBoundsException e) {
                    // Tous les dessins ont été effectuer
                    // Fermeture du thread
                }
            });
            threads.add(thread);
            thread.start();
        }

        for(Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {}
        }

        if(calculScene.getCalculsRestants() > 0) {
            image = continuerCalcul(calculScene, image);
        }

        return image;
    }


    /**
     * methode qui permet au service de calcul d'envoyer leur class
     * @param service
     */
    @Override
    public synchronized void enregistrerServiceCalcul(ServiceCalcul service) throws RemoteException {
        try {
            String clientHost = getClientHost();

            if (service != null)
                System.out.println("\u001B[31m" + clientHost + ": ServiceCalcul fourni == null \u001B[0m");

            if (calculateurs.contains(service))
                System.out.println("\u001B[31m" + clientHost + ": ServiceCalcul fourni déjà connu par le serveur central \u001B[0m");

            calculateurs.add(service);
            System.out.println("\u001B[32m" + clientHost + ": Enregistrement \u001B[0m");

        }catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
    }

    public synchronized void retirerServiceCalcul(ServiceCalcul service) {
        calculateurs.remove(service);
    }
}
