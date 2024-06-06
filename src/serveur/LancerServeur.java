package serveur;

import interfaces.ServiceRayTracing;

import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LancerServeur {

    public static void main (String args[]) throws RemoteException {

        Registry reg = LocateRegistry.getRegistry();
        try{
            reg.list();
        }
        catch(ConnectException e) {
            reg = LocateRegistry.createRegistry(1099);
        }

        ServiceRayTracing serviceRayTracing = null;
        try {
            serviceRayTracing = (ServiceRayTracing) reg.lookup("raytracer");
        } catch (NotBoundException e) {
            System.out.println("Entrée \"raytracer\" inconnue");
            throw new RuntimeException(e);
        }
        System.out.println("Connecter !");

    }
}
