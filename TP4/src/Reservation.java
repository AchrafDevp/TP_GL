

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation{
    enum State{
        EN_ATTENTE,
        PAYEE,
        CONFIRMEE,
        ANNULEE
    }

    private Date date;
    private String identifiant;
    private State etat;
    private Vol vol;
    private Client client;
    private Passager passager;

    public Reservation(Vol vol, Client client, Passager passager, String identifiant){
        this.vol = vol;
        this.client = client;
        this.passager = passager;
        this.identifiant = identifiant;
        this.etat = State.EN_ATTENTE;
        date = new Date(System.currentTimeMillis());
	
    }

    public void annuler(){
        switch(etat){
            case EN_ATTENTE:
                etat = State.ANNULEE;
            case PAYEE:
                etat = State.ANNULEE;
                /*Remboursement */
		client.ajouterRemboursement(identifiant, vol.getPrix());
            case ANNULEE:
                System.out.println("The reservation is already cancelled");
            case CONFIRMEE:
                etat = State.ANNULEE;
        }
    }
    public void confirmer() throws IllegalStateException{
        switch(etat){
            case EN_ATTENTE:
                throw new IllegalStateException("You need to pay before confirming");
            case PAYEE:
                etat = State.CONFIRMEE;
            case ANNULEE:
                throw new IllegalStateException("The reservation was cancelled.");
            case CONFIRMEE:
                System.out.println("You have already confirmed");
        }
    }
    public void payer() throws IllegalStateException{
        switch(etat){
            case EN_ATTENTE:
		client.ajouterPaiement(identifiant, vol.getPrix());
                etat = State.PAYEE;	
            case PAYEE:
                throw new IllegalStateException("You have already paid. However, you haven't paid yet.");
            case ANNULEE:
                throw new IllegalStateException("The reservation was cancelled");
            case CONFIRMEE:
                throw new IllegalStateException("You have already paid and the reservation was confirmed.");
        }
    }

    public boolean aIdentifiant(String id){
        if (identifiant.equals(id)) return true;
        return false;
    }

    
    

    
}
