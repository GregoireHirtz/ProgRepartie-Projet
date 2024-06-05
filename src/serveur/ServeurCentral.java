package serveur;

import interfaces.ServiceCalcul;
import interfaces.ServiceRayTracing;
import raytracer.Disp;
import raytracer.Image;
import raytracer.Scene;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ServeurCentral implements ServiceRayTracing {

    private ArrayList<ServiceCalcul> calculateurs = new ArrayList<ServiceCalcul>();

    /**
     * methode qui permet de demande au serveur central le calcul de la scene fournie
     * @param scene
     * @return
     */
    @Override
    public Image calculerScene(Scene scene, int largeur, int hauteur) {
        CalculScene calculScene = new CalculScene(scene, hauteur, largeur);
        Image res = new Image(largeur, hauteur);

        for(ServiceCalcul serviceCalcul : calculateurs) {
            new Thread(() -> {
                Calcul calcul = null;
                try{
                    calcul = calculScene.getCalcul();
                    Image image = serviceCalcul.effectuerCalcul(calcul.scene, calcul.x, calcul.y, calcul.w, calcul.h);
                    // TODO -> Ajouter l'image au resultat
                }
                catch(RemoteException e) {
                    if(calcul != null) {
                        calculScene.ajouterCalcul(calcul);
                    }
                }
            }).start();
        }

        return res;
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
