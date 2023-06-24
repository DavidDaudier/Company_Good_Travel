
package Gestion_Colis;

import java.util.ArrayList;

public interface Operation_Colis<A> {
    void enregistrer(A o);
    boolean modifier(A o);
    ArrayList<A> afficher();
}
