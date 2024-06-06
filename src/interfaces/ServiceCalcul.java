package interfaces;

import raytracer.Image;
import raytracer.Scene;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceCalcul extends Remote {
    Image effectuerCalcul(Scene scene, int x, int y, int largeur, int hauteur) throws RemoteException;
}
