package ch4.item17.ex2.packageA;

//Subject : Minimize mutability
//Making immutable class without using final
public class Complex2 {
    private final double re;
    private final double im;

    Complex2(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public static Complex2 valueOf(double re, double im) {
        return new Complex2(re, im);
    }


    public double realPart() { return re; }

    public double imaginaryPart() { return im; }

    public Complex2 plus(Complex2 c) {
        return new Complex2(re + c.re, im + c.im);
    }

    public Complex2 minus(Complex2 c) {
        return new Complex2(re - c.re, im - c.im);
    }

    public Complex2 times(Complex2 c) {
        return new Complex2(re * c.re - im * c.im, re * c.im + im * c.re);
    }

    public Complex2 divideBy(Complex2 c) {
        double tmp = c.re * c.re + c.im * c.im;
        return new Complex2((re * c.re + im * c.im) / tmp, (im * c.re - re
                * c.im)
                / tmp);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Complex2))
            return false;
        Complex2 c = (Complex2) o;

        return Double.compare(re, c.re) == 0 && Double.compare(im, c.im) == 0;
    }

    @Override
    public int hashCode() {
        return 31 * Double.hashCode(re) + Double.hashCode(im);
    }

    @Override
    public String toString() {
        return "(" + re + " + " + im + "i)";
    }
}