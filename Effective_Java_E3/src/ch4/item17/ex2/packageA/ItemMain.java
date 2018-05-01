package ch4.item17.ex2.packageA;

//Subject : Builder Pattern for class hierarchies
class ItemMain {
    public static void main(String[] args) {

        //test for immutable class without using final
        //Complex2 test = Complex2.valueOf(0, 0);
        //packageB.Complex3 can't be extended from Complex2

        int[] c =  new int[2];
        c[0]=1;
        c[1]=2;


        BigInt b = new BigInt(5,c);

        System.out.println(b.negate().getsig());
        System.out.println(b.getsig());
        System.out.println(b.negate().getmag());
        System.out.println(b.getmag());

    }
}