package ch2.item6.ex1;
/**
 * @author Roy Kim
 */

//Subject : Avoid creating unnecesary objects
public class Another {

    String s1 = new String("bikini");
    String s2;

    Another(){
        s2 = new String("bikini2");
    }

    public void print() {
        System.out.println("An -> " + Integer.toHexString(s1.hashCode()));
        System.out.println("An -> " + Integer.toHexString(s2.hashCode()));
    }

}
