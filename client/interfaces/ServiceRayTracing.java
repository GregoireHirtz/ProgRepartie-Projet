package interfaces;

import raytracer.Image;
import raytracer.Scene;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceRayTracing extends Remote {
    Image calculerScene(Scene scene, int largeur, int hauteur) throws RemoteException;
    void enregistrerServiceCalcul(ServiceCalcul service) throws RemoteException;
}
