
package roman;


import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class RomanNumber  extends Number implements Comparable<Object> {

  public static final long serialVersionUID = 1L;

  // Table des symboles
  private static final LinkedHashMap<String, Integer> SYMBOLS = new LinkedHashMap<>();

  static {
    SYMBOLS.put("M", 1000);
    SYMBOLS.put("CM", 900);
    SYMBOLS.put("D", 500);
    SYMBOLS.put("CD", 400);
    SYMBOLS.put("C", 100);
    SYMBOLS.put("XC", 90);
    SYMBOLS.put("L", 50);
    SYMBOLS.put("XL", 40);
    SYMBOLS.put("X", 10);
    SYMBOLS.put("IX", 9);
    SYMBOLS.put("V", 5);
    SYMBOLS.put("IV", 4);
    SYMBOLS.put("I", 1);
  }

  // Expression reguliere de validation
  private static final Pattern VALIDATION_RE = Pattern.compile(
          "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");

  private final int value;


  public RomanNumber(String romanValue) throws IllegalArgumentException {
  Matcher m = VALIDATION_RE.matcher(romanValue);
  if (!m.matches())
    throw new IllegalArgumentException();
  this.value = fromRoman(romanValue);
  }

  public RomanNumber(int value) throws IllegalArgumentException {

    if (value <=0 || value>3999)
     throw new IllegalArgumentException();
    this.value = value;
  }

  /**
   * @{inheritDoc}
   */
  @Override
  public double doubleValue() {
    Integer i = new Integer(value);
    return i.doubleValue();
  }

  /**
   * @{inheritDoc}
   */
  @Override
  public float floatValue() {
    Integer i = new Integer(value);
    return i.floatValue();
  }

  /**
   * @{inheritDoc}
   */
  @Override
  public int intValue() {
  return value;
  }

  /**
   * @{inheritDoc}
   */
  @Override
  public long longValue() {
    Integer i = new Integer(value);
    return i.longValue();
  }

  /**
   * @{inheritDoc}
   */
  @Override
  public short shortValue() {
    Integer i = new Integer(value);
    return i.shortValue();
  }

  /**
   * @{inheritDoc}
   */
  @Override
  public byte byteValue() {
    Integer i = new Integer(value);
    return i.byteValue();
  }




  public static RomanNumber valueOf(String roman) {
    return new RomanNumber(roman);
  }

  public static RomanNumber valueOf(int value) {
    return new RomanNumber(value);
  }


  private static int fromRoman(String romanValue) {
    int resultat = 0;
    int index = 0;


    Iterator<Map.Entry<String, Integer>> symbolsIterator = SYMBOLS.entrySet().iterator();
    while (symbolsIterator.hasNext()){
      Map.Entry<String, Integer> v = symbolsIterator.next();
        while (v.getKey().length() +index <= romanValue.length() && romanValue.substring(index, index + v.getKey().length()).equals(v.getKey())) {
            resultat += v.getValue();
            index += v.getKey().length();
        }
      }

      return resultat;
  }
    private static String toRoman ( int value) {
      String resultat = "";


      for (Map.Entry<String, Integer> v : SYMBOLS.entrySet()) {
        while (value >= v.getValue()) {
          resultat = resultat + v.getKey();
          value = value - v.getValue();
        }
      }
      return resultat;
    }
    @Override
    public String toString () {
      return toRoman(this.value);
    }

    @Override
    public int compareTo(Object n){
      String primitifType = ((Object)n).getClass().getName();
      switch (primitifType){
          case "java.lang.Float":
            if ( floatValue()-(float)n>0)
                return 1;
            else if (floatValue()-(float)n == 0) return 0;
            return -1;

            case "java.lang.Double":
            if (doubleValue()-(double)n>0)
                return 1;
            else if (doubleValue()-(double)n == 0) return 0;
            return -1;
        case "java.lang.Short":
            if (shortValue()-(short)n>0)
                return 1;
            else if (shortValue()-(short)n == 0) return 0;
            return -1;
        case "java.lang.Byte":
            if (byteValue()-(byte)n>0)
                return 1;
            else if (byteValue()-(byte)n == 0) return 0;
            return -1;

            case "java.lang.Long":
            if (longValue()-(long)n>0)
                return 1;
            else if (longValue()-(long)n == 0) return 0;
            return -1;

          case "roman.RomanNumber":
              return value - ((RomanNumber)n).intValue();
          case "java.lang.Integer":
                return value - (int)n;
          default:
              throw new ClassCastException();

      }
    }

}



