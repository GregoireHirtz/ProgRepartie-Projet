package serviceRayTracing;

import interfaces.ServiceRayTracing;

import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LancerServeur {

    public static void main (String args[]) throws RemoteException {

        Registry reg = LocateRegistry.getRegistry();
        try{
            reg.list();
        }
        catch(ConnectException e) {
            reg = LocateRegistry.createRegistry(1099);
        }
        //création du serveur
        ServeurCentral serveurCentral = new ServeurCentral();
        //Ajout du service dans l'annuaire
        ServiceRayTracing serviceRayTracing = (ServiceRayTracing) UnicastRemoteObject.exportObject(serveurCentral,0);
        reg.rebind("raytracer",serviceRayTracing);

    }
}
