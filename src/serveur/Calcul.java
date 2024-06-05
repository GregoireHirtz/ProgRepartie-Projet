package serveur;

import raytracer.Scene;

public class Calcul {

    public Scene scene;
    public int x, y, largeur, hauteur;

    public Calcul(Scene scene, int x, int y, int largeur, int hauteur) {
        this.scene = scene;
        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

}
