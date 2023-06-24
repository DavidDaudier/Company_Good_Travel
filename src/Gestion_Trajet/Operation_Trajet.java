
package Gestion_Trajet;

import java.util.ArrayList;

public interface Operation_Trajet<A> {
    void creerTrajet(A o);
    boolean modifier(A o);
    ArrayList <A> afficher();
    void supprimer(String codeTrajet);
}
