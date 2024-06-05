package interfaces;

import raytracer.Image;
import raytracer.Scene;

public interface ServiceRayTracing {
    Image calculerScene(Scene scene);
    void enregistrerServiceCalcul(ServiceCalcul service);
}
