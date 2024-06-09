import interfaces.ServiceCalcul;
import raytracer.Image;
import raytracer.Scene;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.time.Duration;
import java.time.Instant;

public class ClientCalcul implements ServiceCalcul, Serializable {
    @Override
    public Image effectuerCalcul(Scene scene, int x, int y, int width, int height) throws RemoteException {
        Instant debut = Instant.now();
        System.out.println("Calcul de l'image :\n - Coordonnées : "+ x +"," + y
                + "\n - Taille " + width + "x" + height);
        Image image = scene.compute(x, y, width, height);
        Instant fin = Instant.now();

        long duree = Duration.between(debut, fin).toMillis();

        System.out.println("Image calculée en :"+duree+" ms");

        return image;
    }
}
