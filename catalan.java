import java.math.BigInteger;

class Catalan {
    public static void main(String[] args) {
	    System.out.println(catalan(2));
    }

    public static BigInteger catalan(int n) {
        BigInteger[] catalans = new BigInteger[n + 1];
        catalans[0] = new BigInteger("1");
        for (int i = 1; i <= n; i++) {
            catalans[i] = catalans[i - 1].multiply(
                BigInteger.valueOf(4 * i - 2)).divide(
                BigInteger.valueOf(i + 1));
        }
        return catalans[n];
    }
}