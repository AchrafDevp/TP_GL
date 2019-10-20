import java.util.*;


public class Client {
    private String nom;
    private String contactTel;
    private String reference;
    private Map<String, Double> paiements; /* identifiantRes -> prix*/
    private Map<String, Double> remboursements; /* identifiantRes -> prix */
    private List<Reservation> reservations;
    
    
    public Client(String nomC, String contactTelC, String referenceC){
        nom = nomC;
        contactTel = contactTelC;
        reservations = new ArrayList<Reservation>();
	paiements = new HashMap<String, Double>();
	remboursements = new HashMap<String, Double>();
	reference = referenceC;
	
    }
    
    public void reserver(Vol vol, Passager passager, String identifiant){
        try{
            Reservation res = vol.creerReservation(vol, this, passager, identifiant);
        }catch(IllegalStateException ex){
            System.err.println(ex.getMessage());
        }
    }

    public void confirmerReservation(String idReservation) throws IllegalStateException, IllegalArgumentException{
	findReservation(idReservation).confirmer();
    }

    

    public void payerReservation(String idReservation) throws IllegalArgumentException, IllegalArgumentException{
	findReservation(idReservation).payer();
    }

    public void annulerReservation(String idReservation) throws IllegalArgumentException{
       	findReservation(idReservation).annuler();
    }

    private Reservation findReservation(String id) throws IllegalArgumentException{
        Iterator<Reservation> it = reservations.iterator();
        while (it.hasNext()){
            Reservation res = it.next();
            if (res.aIdentifiant(id)) return res;
        }
        throw new IllegalArgumentException();
    }

    public void ajouterReservation(Reservation reservation){
        reservations.add(reservation);
    }

    public void ajouterPaiement(String id, double prix){
	paiements.put(id, prix);
    }

    public void ajouterRemboursement(String id, double prix){
	remboursements.put(id, prix);
    }
}
