import java.math.BigInteger;

class DoubleFactorial {

    public static BigInteger calcDoubleFactorial(int n) {
        int multiplier = n % 2 == 0 ? 2 : 1;
        BigInteger result = BigInteger.ONE;
        while (multiplier != n + 2) {
            result = result.multiply(BigInteger.valueOf(multiplier));
            multiplier += 2;
        }
        return result;
    }
}