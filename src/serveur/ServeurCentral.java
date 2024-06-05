package serveur;

import interfaces.ServiceCalcul;
import interfaces.ServiceRayTracing;
import raytracer.Disp;
import raytracer.Image;
import raytracer.Scene;

import java.util.ArrayList;

public class ServeurCentral implements ServiceRayTracing {

    private ArrayList<ServiceCalcul> calculateurs = new ArrayList<ServiceCalcul>();

    /**
     * methode qui permet de demande au serveur central le calcul de la scene fournie
     * @param scene
     * @return
     */
    @Override
    public Image calculerScene(Scene scene, int hauteur, int largeur) {

        ArrayList<Calcul> blocs = new ArrayList<>();

        for (int i=0; i<=largeur; i+=99){
            for (int j=0; j<=hauteur; j+=99){
                blocs.add(new Calcul(scene, i, j));
            }
        }


    }

    public synchronized Calcul getCalcul() {

    }



    /**
     * methode qui permet au service de calcul d'envoyer leur class
     * @param service
     */
    @Override
    public synchronized void enregistrerServiceCalcul(ServiceCalcul service) {
        if (service != null)
            System.out.println("ServiceCalcul fourni == null");

        if (calculateurs.contains(service))
            System.out.println("ServiceCalcul fourni déjà connu par le serveur central");

        calculateurs.add(service);
    }
}
