package serveur;

import raytracer.Scene;

import java.util.ArrayList;

import static java.lang.Math.min;

public class CalculScene {
    ArrayList<Calcul> calculs;

    public CalculScene(Scene scene, int hauteur, int largeur) {
        this.calculs = new ArrayList<>();

        int l, h;

        for (int i = 0; i <= largeur; i += l){
            l = min(100, largeur - i);
            for (int j = 0; j <= hauteur; j += h){
                h = min(100, hauteur - j);
                calculs.add(new Calcul(scene, i, j, l, h));
            }
        }
    }

    public synchronized Calcul getCalcul() {
        Calcul res = calculs.get(0);
        calculs.remove(0);
        return res;
    }

    public synchronized void ajouterCalcul(Calcul calcul) {
        calculs.add(calcul);
    }
}