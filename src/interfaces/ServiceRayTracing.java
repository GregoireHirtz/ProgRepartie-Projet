package interfaces;

import raytracer.Image;
import raytracer.Scene;

public interface ServiceRayTracing {
    Image calculerScene(Scene scene, int largeur, int hauteur);
    void enregistrerServiceCalcul(ServiceCalcul service);
}
