package client;

import interfaces.ServiceRayTracing;
import raytracer.Disp;
import raytracer.Image;
import raytracer.Scene;

import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LancerClient {
    public static void main (String args[]) throws RemoteException {

        System.out.println("Connexion au service de Raytracing...");

        Registry reg = LocateRegistry.getRegistry(args[0]);
        try{
            String[] registerEntries = reg.list();

            System.out.println("--Annuaire--");
            for(String entry : registerEntries) {
                System.out.println(entry);
            }
            System.out.println("--Annuaire--");
        }
        catch(ConnectException e) {
            System.out.println("Annuaire introuvable");
        }
        //Recupere l'interface distante dans l'annuaire de la machine (distant)
        ServiceRayTracing serveur = null;
        try {
            serveur = (ServiceRayTracing) reg.lookup("raytracer");
        } catch (NotBoundException e) {
            System.out.println("Entrée \"raytracer\" inconnue");
            throw new RuntimeException(e);
        }
        System.out.println("Connecter !");

        System.out.println("Création de la scene a calculer...");
        int largeur = 1028, hauteur = 1028;
        String fichier_description= "ressource/custom1.txt";
        Scene scene = new Scene(fichier_description, largeur, hauteur);

        System.out.println("Calcul de la scene par le service...");
        Image image = serveur.calculerScene(scene, largeur, hauteur);

        System.out.println("Terminer !");
        Disp disp = new Disp("Raytracer", largeur, hauteur);
        disp.setImage(image, 0, 0);
    }
}
