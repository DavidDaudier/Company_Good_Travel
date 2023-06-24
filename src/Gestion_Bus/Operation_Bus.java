
package Gestion_Bus;

import java.util.ArrayList;

public interface Operation_Bus<A> {
    void enregistrer(A o);
    boolean modifier(A o);
    ArrayList<A> afficher();
    void supprimer(String codeBus);
}
