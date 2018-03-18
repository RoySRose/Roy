package ch3.item10.ex1;
/**
 * @author Roy Kim
 */

//Subject : Obey the general contract when overriding equals
//violates symmetry
public class Main {
    public static void main(String[] args) {
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String s = "polish";
        System.out.println(cis.equals(s) + "  " + s.equals(cis));
    }
}
