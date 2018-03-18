package ch3.item11;
/**
 * @author Roy Kim
 */

//Subject :  Always override hashCode when you override equals
import java.util.HashMap;
import java.util.Map;

public final class PhoneNumber {
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;

    public PhoneNumber(int areaCode, int prefix, int lineNumber) {
        this.areaCode = (short) areaCode;
        this.prefix = (short) prefix;
        this.lineNumber = (short) lineNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PhoneNumber))
            return false;
        PhoneNumber pn = (PhoneNumber) o;
        return pn.lineNumber == lineNumber && pn.prefix == prefix
                && pn.areaCode == areaCode;
    }

    // Broken - no hashCode method!

    // A decent hashCode method - Page 48
    // @Override public int hashCode() {
    // int result = 17;
    // result = 31 * result + areaCode;
    // result = 31 * result + prefix;
    // result = 31 * result + lineNumber;
    // return result;
    // }

    // Lazily initialized, cached hashCode - Page 49
    // private volatile int hashCode; // (See Item 71)
    //
    // @Override public int hashCode() {
    // int result = hashCode;
    // if (result == 0) {
    // result = 17;
    // result = 31 * result + areaCode;
    // result = 31 * result + prefix;
    // result = 31 * result + lineNumber;
    // hashCode = result;
    // }
    // return result;
    // }

    public static void main(String[] args) {
        Map<PhoneNumber, String> m = new HashMap<PhoneNumber, String>();
        m.put(new PhoneNumber(111, 222, 3333), "Roy");
        System.out.println(m.get(new PhoneNumber(111, 222, 3333)));
    }
}
