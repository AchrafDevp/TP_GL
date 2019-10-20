import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Vol {
    enum State{
        OUVERT,
        FERME
    }

    private State etat;
    private String identifiant;
    private ZonedDateTime depart;
    private ZonedDateTime arrivee;
    private List<Reservation> reservations;
    private int maxReservation;
    private double prixReservation; /* en euro */ 
    
    public Vol(ZonedDateTime depart, ZonedDateTime arrivee, int maxReservation, double prixReservation, String identifiant){
        this.depart = depart;
        this.arrivee = arrivee;
	this.maxReservation = maxReservation;
        etat = State.OUVERT;
        reservations = new ArrayList<Reservation>();	
	this.identifiant = identifiant;
	this.prixReservation = prixReservation;
    }

    /* La duree est en minute */
    public int duree(){
        return arrivee.getMinute()-depart.getMinute();
    }

    public void ouvrir() throws IllegalStateException{
        if (etat.equals(State.OUVERT)) throw new IllegalStateException("The flight is already open for reservation");
        else etat = State.OUVERT;
    }

    public void fermer() throws IllegalStateException{
        if (etat.equals(State.FERME)) throw new IllegalStateException("The flight is already closed for reservation");
        else etat = State.FERME;
    }

    public Reservation creerReservation(Vol vol, Client client, Passager passager, String identifiant) throws IllegalStateException{
        if (etat.equals(State.FERME)) throw new IllegalStateException("The flight is closed for reservation");
        else{
            Reservation res = new Reservation(vol, client, passager, identifiant);
            reservations.add(res);
	    passager.ajouterReservation(res);
	    client.ajouterReservation(res);
	    if (reservations.size() == maxReservation) etat = State.FERME;
            return res;
        }	
    }

    protected void setPrixReservation(double prix){
	prixReservation = prix;
    }

    public double getPrix(){
	return prixReservation;
    }
    
}
    
