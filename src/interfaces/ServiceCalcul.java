package interfaces;

import raytracer.Image;
import raytracer.Scene;

public interface ServiceCalcul {
    Image effectuerCalcul(Scene scene, int x, int y, int x2, int y2);
}
