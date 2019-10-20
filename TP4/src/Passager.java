

import java.util.ArrayList;
import java.util.List;

public class Passager {
    private String nom;
    private String contact;
    private List<Reservation> reservations;

    public Passager(String nom, String contact){
        this.nom = nom;
        this.contact = contact;
        reservations = new ArrayList<Reservation>();
    }

    public void ajouterReservation(Reservation reservation){
        reservations.add(reservation);
    }

    

}
