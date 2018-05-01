package ch4.item17.ex2.packageA;

//Subject : Minimize mutability
//Making immutable class without using final
public class Complex3 extends Complex2
{
    //can't extend Complex2
    public Complex3(double re, double im) {
        //return Complex2.valueOf(0, 0);
        super(re, im);
    }

}