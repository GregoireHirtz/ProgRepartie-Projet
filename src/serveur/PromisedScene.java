package serveur;

import java.util.ArrayList;

public class PromisedScene {
    ArrayList<Calcul> calculs;


    public synchronized Calcul getCalcul() {
        Calcul res = calculs.get(0);
        calculs.remove(0);
        return res;
    }

    public synchronized void ajouterCalcul(Calcul calcul) {
        calculs
    }
}
