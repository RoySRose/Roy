package ch2.item6.ex1;
/**
 * @author Roy Kim
 */

//Subject : Avoid creating unnecesary objects
public class Main {
    public static void main(String[] args) {

        String s1 = new String("bikini");
        String s2 = new String("bikini");

        System.out.println(Integer.toHexString(s1.hashCode()));
        System.out.println(Integer.toHexString(s2.hashCode()));

        String[] str = new String[5];
        String oneStr;
        for(int i=0; i<5; i++){
            oneStr = new String("One");
            System.out.println(Integer.toHexString(oneStr.hashCode()));
            //str[i] = "Test";
            str[i] = new String("Test");
        }

        //for(String s : str){
        for(int i=0; i<5; i++){
            System.out.println(Integer.toHexString(str[i].hashCode()));
        }
        Another another= new Another();
        another.print();
        Another another2= new Another();
        another2.print();
    }

}
