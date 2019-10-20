import org.junit.*;
import java.time.ZonedDateTime;

public class VolTest {

    @Test
    public void dureeValidation(){
	ZonedDateTime dt1 = ZonedDateTime.parse("2019-12-20T12:30:40Z[GMT]");
	ZonedDateTime dt2 = ZonedDateTime.parse("2019-12-20T12:44:40Z[GMT]");
	Vol vol = new Vol(dt1, dt2, 10, 220, "v001");
	Assert.assertTrue(vol.duree() == 14);
    }

    
    @Test(expected = IllegalStateException.class)
    public void reserverVolFerme(){
	Client client1 = new Client("NassimLamara", "066837238", "c001");
	Client client2 = new Client("AchrafLOUIZA", "064392832", "c002");
	Vol vol = new Vol(ZonedDateTime.parse("2019-12-20T12:30:40Z[GMT]"), ZonedDateTime.parse("2019-12-20T17:20:40Z[GMT]"), 1, 220, "v001");
	Passager passager = new Passager("AymenMelakhessou", "045239132");
	vol.creerReservation(vol, client1, passager, "r001");
	vol.creerReservation(vol, client2, passager, "r002"); 
    }
    
}
