
package Gestion_Reservation;

import java.util.ArrayList;

public interface Operation_Reservation<A> {
    void enregistrer(A o);
    boolean modifier(A o);
    ArrayList<A> afficher();
    void supprimer(String numReservation);
}
