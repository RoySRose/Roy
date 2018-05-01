package ch4.item17.ex2.packageA;



public class BigInt  {
    final int signum;
    final int[] mag;

    BigInt(int signum, int[] mag){
        this.signum = signum;
        this.mag = mag;
    }
    public BigInt negate() {
        return new BigInt( -this.signum, this.mag);
    }
    public int[] getmag(){
        return mag;
    }
    public int getsig(){
        return signum;
    }
}