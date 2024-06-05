package serveur;

import raytracer.Scene;

public class Calcul {

    private Scene scene;
    private int x, y, w, h;

    public Calcul(Scene scene, int x, int y) {
        this.x = x;
        this.y = y;
        this.w = x+=99;
        this.h = y+=99;
    }

}
