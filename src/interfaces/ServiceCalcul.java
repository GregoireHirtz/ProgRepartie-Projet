package interfaces;

import raytracer.Image;
import raytracer.Scene;

import java.rmi.RemoteException;

public interface ServiceCalcul {
    Image effectuerCalcul(Scene scene, int x, int y, int largeur, int hauteur) throws RemoteException;
}
