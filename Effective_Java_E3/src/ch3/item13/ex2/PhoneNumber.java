package ch3.item13.ex2;
/**
 * @author Roy Kim
 */

//Subject : Override clone judiciously
public class PhoneNumber implements Cloneable {
    private final short areaCode;
    private final short prefix;
    private final short lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = (short) areaCode;
        this.prefix = (short) prefix;
        this.lineNum = (short) lineNum;
    }

//    @Override
//    public PhoneNumber clone() {
//        try {
//            System.out.println("cloning");
//            return new PhoneNumber(this.areaCode, this.prefix, this.lineNum);
//            //return (PhoneNumber) super.clone();
//        } catch (Exception e) {
//            throw new AssertionError(); // Can't happen
//        }
//    }

    @Override
    public String toString() {
        return String.format("(%03d) %03d-%04d", areaCode, prefix, lineNum);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;
        if (!(o instanceof PhoneNumber))
            return false;
        PhoneNumber pn = (PhoneNumber) o;
        return pn.lineNum == lineNum &&
                pn.areaCode == areaCode &&
                pn.prefix == prefix;
    }

    public static void main(String[] args) {
        PhoneNumber clone1 = new PhoneNumber(111, 222, 3333);
//        PhoneNumber clone2 = clone1.clone();
//
//        System.out.println(clone1 != clone2); //true
//        System.out.println(clone1.getClass() == clone2.getClass()); //true
//        System.out.println(clone1.equals(clone2)); //true

    }
}