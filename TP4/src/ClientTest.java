import org.junit.*;
import java.time.ZonedDateTime;

public class ClientTest {

    @Test(expected = IllegalStateException.class)
    public void confirmerSansPayer(){
	Client client = new Client("NassimLamara", "066837238", "c001");
	Vol vol = new Vol(ZonedDateTime.parse("2019-12-20T12:30:40Z[GMT]"), ZonedDateTime.parse("2019-12-20T17:20:40Z[GMT]"), 1, 220, "v001");
	Passager passager = new Passager("AymenMelakhessou", "045239132");
	client.reserver(vol, passager, "r001");
	client.confirmerReservation("r001");
    }


    @Test(expected = IllegalStateException.class)
    public void payer2fois(){
	Client client = new Client("NassimLamara", "066837238", "c001");
	Vol vol = new Vol(ZonedDateTime.parse("2019-12-20T12:30:40Z[GMT]"), ZonedDateTime.parse("2019-12-20T17:20:40Z[GMT]"), 1, 220, "v001");
	Passager passager = new Passager("AymenMelakhessou", "045239132");
	Reservation res = vol.creerReservation(vol, client, passager, "r001");
	client.payerReservation("r001");
	client.payerReservation("r001");
    }

}
