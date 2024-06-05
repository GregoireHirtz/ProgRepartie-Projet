package interfaces;

import raytracer.Image;
import raytracer.Scene;

import java.rmi.RemoteException;

public interface ServiceRayTracing {
    Image calculerScene(Scene scene, int largeur, int hauteur) throws RemoteException;
    void enregistrerServiceCalcul(ServiceCalcul service) throws RemoteException;
}
