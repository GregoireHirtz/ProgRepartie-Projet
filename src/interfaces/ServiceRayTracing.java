package interfaces;

import raytracer.Image;
import raytracer.Scene;

public interface ServiceRayTracing {
    Image calculerScene(Scene scene, int hauteur, int largeur);
    void enregistrerServiceCalcul(ServiceCalcul service);
}
