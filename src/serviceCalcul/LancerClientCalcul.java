package serviceCalcul;

import interfaces.ServiceCalcul;
import interfaces.ServiceRayTracing;

import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LancerClientCalcul {

    public static void main (String args[]) throws RemoteException {


        Registry reg = LocateRegistry.getRegistry(args[0]);

        ServiceRayTracing serviceRayTracing =null;
        try {
            serviceRayTracing = (ServiceRayTracing) reg.lookup("raytracer");
        } catch (NotBoundException e) {
            System.out.println("Entrée \"raytracer\" inconnue");
            throw new RuntimeException(e);
        }
        System.out.println("Connecter !");

        //création d'un objet clientCalcul
        ClientCalcul clientCalcul = new ClientCalcul();
        //création d'une interface exportable et l'assigne à un port automatique (0)
        ServiceCalcul serviceCalcul = (ServiceCalcul) UnicastRemoteObject.exportObject((Remote) clientCalcul, 0);

        try{
            serviceRayTracing.enregistrerServiceCalcul(serviceCalcul);
            System.out.println("Le service de calcul a été enregistrer avec succès ! ");
        } catch(RemoteException e) {System.out.println("Service de raytracing injoignable");}
    }
}
