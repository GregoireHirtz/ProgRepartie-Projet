import raytracer.Scene;

public class Calcul implements Comparable<Calcul> {

    public Scene scene;
    public int x, y, largeur, hauteur;

    public Calcul(Scene scene, int x, int y, int largeur, int hauteur) {
        this.scene = scene;
        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    @Override
    public int compareTo(Calcul o) {

        int val = scene.toString().compareTo(o.scene.toString());
        if(val != 0) return val;

        val = Integer.compare(x, o.x);
        if(val != 0) return val;

        val = Integer.compare(y, o.y);
        if(val != 0) return val;

        val = Integer.compare(largeur, o.largeur);
        if(val != 0) return val;

        val = Integer.compare(hauteur, o.hauteur);
        if(val != 0) return val;

        return 0;
    }
}
