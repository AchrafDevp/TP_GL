package roman;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.*;

import static junit.framework.TestCase.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class RomanNumberTest {
  public static final HashMap<Integer, String> KNOWN_VALUES = new HashMap<>();
  static {
    KNOWN_VALUES.put(1, "I");
    KNOWN_VALUES.put(2, "II");
    KNOWN_VALUES.put(3, "III");
    KNOWN_VALUES.put(4, "IV");
    KNOWN_VALUES.put(5, "V");
    KNOWN_VALUES.put(6, "VI");
    KNOWN_VALUES.put(7, "VII");
    KNOWN_VALUES.put(8, "VIII");
    KNOWN_VALUES.put(9, "IX");
    KNOWN_VALUES.put(10, "X");
    KNOWN_VALUES.put(50, "L");
    KNOWN_VALUES.put(100, "C");
    KNOWN_VALUES.put(500, "D");
    KNOWN_VALUES.put(1000, "M");
    KNOWN_VALUES.put(31, "XXXI");
    KNOWN_VALUES.put(148, "CXLVIII");
    KNOWN_VALUES.put(294, "CCXCIV");
    KNOWN_VALUES.put(312, "CCCXII");
    KNOWN_VALUES.put(421, "CDXXI");
    KNOWN_VALUES.put(528, "DXXVIII");
    KNOWN_VALUES.put(621, "DCXXI");
    KNOWN_VALUES.put(782, "DCCLXXXII");
    KNOWN_VALUES.put(870, "DCCCLXX");
    KNOWN_VALUES.put(941, "CMXLI");
    KNOWN_VALUES.put(1043, "MXLIII");
    KNOWN_VALUES.put(1110, "MCX");
    KNOWN_VALUES.put(1226, "MCCXXVI");
    KNOWN_VALUES.put(1301, "MCCCI");
    KNOWN_VALUES.put(1485, "MCDLXXXV");
    KNOWN_VALUES.put(1509, "MDIX");
    KNOWN_VALUES.put(1607, "MDCVII");
    KNOWN_VALUES.put(1754, "MDCCLIV");
    KNOWN_VALUES.put(1832, "MDCCCXXXII");
    KNOWN_VALUES.put(1993, "MCMXCIII");
    KNOWN_VALUES.put(2074, "MMLXXIV");
    KNOWN_VALUES.put(2152, "MMCLII");
    KNOWN_VALUES.put(2212, "MMCCXII");
    KNOWN_VALUES.put(2343, "MMCCCXLIII");
    KNOWN_VALUES.put(2499, "MMCDXCIX");
    KNOWN_VALUES.put(2574, "MMDLXXIV");
    KNOWN_VALUES.put(2646, "MMDCXLVI");
    KNOWN_VALUES.put(2723, "MMDCCXXIII");
    KNOWN_VALUES.put(2892, "MMDCCCXCII");
    KNOWN_VALUES.put(2975, "MMCMLXXV");
    KNOWN_VALUES.put(3051, "MMMLI");
    KNOWN_VALUES.put(3185, "MMMCLXXXV");
    KNOWN_VALUES.put(3250, "MMMCCL");
    KNOWN_VALUES.put(3313, "MMMCCCXIII");
    KNOWN_VALUES.put(3408, "MMMCDVIII");
    KNOWN_VALUES.put(3501, "MMMDI");
    KNOWN_VALUES.put(3610, "MMMDCX");
    KNOWN_VALUES.put(3743, "MMMDCCXLIII");
    KNOWN_VALUES.put(3844, "MMMDCCCXLIV");
    KNOWN_VALUES.put(3888, "MMMDCCCLXXXVIII");
    KNOWN_VALUES.put(3940, "MMMCMXL");
    KNOWN_VALUES.put(3999, "MMMCMXCIX");
  }

    // String -> RomanNumber
    @Test
    public void fromRomanKnownValues() {
	for (Map.Entry<Integer, String> v : KNOWN_VALUES.entrySet()) {
	    assertThat(RomanNumber.valueOf(v.getValue()).intValue(), is(v.getKey()));
	}
    }


    // Decimal -> RomanNumber
    @Test
    public void fromDecimalKnownValues(){
      for (Map.Entry<Integer, String> v : KNOWN_VALUES.entrySet()) {
        assertThat(RomanNumber.valueOf(v.getKey()).toString(), is(v.getValue()));
      }
    }

    
    // int -> RomanNumber
    @Test(expected = IllegalArgumentException.class)
    public void toRomanZero() {

       RomanNumber.valueOf(0);
      }



    @Test(expected = IllegalArgumentException.class)
    public void toRomanNegatif() { RomanNumber.valueOf(-1 * (1 + new Random().nextInt(4000))); }
    

    
    @Test(expected = IllegalArgumentException.class)
    public void toRomanOutOfInterval(){
	RomanNumber.valueOf(4000 + new Random().nextInt(9999));
    }
    
    @Test(expected =  IllegalArgumentException.class)
    public void toIntRomanTooMuchRepitition(){
	RomanNumber.valueOf("IIII");
    }

    @Test(expected = IllegalArgumentException.class)
    public void toIntRomanSymbolrepetition() { RomanNumber.valueOf("VV"); }

    @Test(expected = IllegalArgumentException.class)
    public void toIntRomanIncorrectOrder() { RomanNumber.valueOf("VX");}

    @Test
    public void validateConversion(){
      for (int i=1; i<4000; i++){
        assertThat(RomanNumber.valueOf(RomanNumber.valueOf(i).toString()).intValue(), is(i));
      }
    }


    @Test
    public void toRomanOnlyCapital(){
	  for (int i=1; i<3999; i++){
	    assertThat(RomanNumber.valueOf(i).toString(), is(RomanNumber.valueOf(i).toString().toUpperCase()));
      }
    }


    @Test(expected = IllegalArgumentException.class)
    public void fromRomanOnlyCapital(){
      RomanNumber.valueOf("iV");
    }

    @Test
    public void romanToDouble(){
      assertEquals(RomanNumber.valueOf(1000).doubleValue(), (double)1000);
    }

    @Test
    public void romanToFloat(){
      assertEquals(RomanNumber.valueOf(100).floatValue(), (float)100);
    }

    @Test
    public void romanToLong(){
      assertEquals(RomanNumber.valueOf(3999).longValue(), (long)3999);
    }

    @Test
    public void romanToInt(){
      assertEquals(RomanNumber.valueOf(2000).intValue(), 2000);
    }

    @Test
    public void romanToByte(){
      assertEquals(RomanNumber.valueOf(1200).byteValue(), (byte)1200);
    }

    @Test
    public void romanToShort(){
      assertEquals(RomanNumber.valueOf(1300).shortValue(), (short)1300);
    }

    @Test
    public void compareTest(){
      assertTrue(RomanNumber.valueOf(123).compareTo(123) == 0);
      assertTrue(RomanNumber.valueOf(123).compareTo(123.2) < 0);
      assertTrue(RomanNumber.valueOf(123).compareTo(RomanNumber.valueOf(1220))<0);
  }
  @Test(expected = ClassCastException.class)
  public void compareIllegalType(){
    RomanNumber.valueOf(123).compareTo("kjh");
  }

}

