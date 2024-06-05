package serveur;

import raytracer.Scene;

public class Calcul {

    public Scene scene;
    public int x, y, w, h;

    public Calcul(Scene scene, int x, int y, int largeur, int hauteur) {
        this.scene = scene;
        this.x = x;
        this.y = y;
        this.w = largeur;
        this.h = hauteur;
    }

}
