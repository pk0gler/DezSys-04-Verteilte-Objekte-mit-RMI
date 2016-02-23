package command;

import callback.CalculateCallback;

import java.io.Serializable;
import java.math.BigDecimal;
import java.rmi.RemoteException;

/**
 * This command calculates Pi up to a certain decimal.
 *
 * @author Alexandru Jaravete
 */
public class CalculatePi implements Command, Serializable {
    private static final long serialVersionUID = 2478264756614L;

    /**
     * constants used in pi computation
     */
    private static final BigDecimal FOUR = BigDecimal.valueOf(4);

    /**
     * rounding mode to use during pi computation
     */
    private static final int roundingMode = BigDecimal.ROUND_HALF_EVEN;

    /**
     * digits of precision after the decimal point
     */
    private final int digits;

    /**
     * result of calculating Pi
     */
    private BigDecimal result;

    /**
     * object to call back when done with calculating Pi
     */
    private CalculateCallback callback;

    /**
     * The constructor prepares all information's for the calculation.
     *
     * @param digits   the number of decimal to be calculated
     * @param callback is called after done with calculating Pi
     */
    public CalculatePi(int digits, CalculateCallback callback) {
        this.digits = digits;
        this.callback = callback;
    }

    @Override
    public void execute() {
        calculate();
        returnResult();
    }

    /**
     * Compute the value of pi to the specified number of
     * digits after the decimal point.  The value is
     * computed using Machin's formula:
     * pi/4 = 4*arctan(1/5) - arctan(1/239)
     * and a power series expansion of arctan(x) to
     * sufficient precision.
     */
    public void calculate() {
        //The calculation is from the Java RMI Tutorial from Oracle
        int scale = digits + 5;
        BigDecimal arctan1_5 = arctan(5, scale);
        BigDecimal arctan1_239 = arctan(239, scale);
        BigDecimal pi = arctan1_5.multiply(FOUR).subtract(arctan1_239).multiply(FOUR);
        result = pi.setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Compute the value, in radians, of the arctangent of
     * the inverse of the supplied integer to the specified
     * number of digits after the decimal point.  The value
     * is computed using the power series expansion for the
     * arc tangent:
     * arctan(x) = x - (x^3)/3 + (x^5)/5 - (x^7)/7 +
     * (x^9)/9 ...
     *
     * @param inverseX
     * @param scale
     * @return arctangent in radians
     */
    public BigDecimal arctan(int inverseX, int scale) {
        //The calculation is from the Java RMI Tutorial from Oracle
        BigDecimal result, numer, term;
        BigDecimal invX = BigDecimal.valueOf(inverseX);
        BigDecimal invX2 = BigDecimal.valueOf(inverseX * inverseX);

        numer = BigDecimal.ONE.divide(invX, scale, roundingMode);

        result = numer;
        int i = 1;
        do {
            numer = numer.divide(invX2, scale, roundingMode);
            int denom = 2 * i + 1;
            term = numer.divide(BigDecimal.valueOf(denom), scale, roundingMode);
            if ((i % 2) != 0) {
                result = result.subtract(term);
            } else {
                result = result.add(term);
            }
            i++;
        } while (term.compareTo(BigDecimal.ZERO) != 0);
        return result;
    }

    /**
     * Returns the result to the callback object.
     */
    public void returnResult() {
        try {
            callback.receiveResult(result);
        } catch (RemoteException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }
}
