/**	The class AReal does arithmetic with doubles.
 */

public class AReal extends AbstractArithmetic {
    public final static String POINT = ".";
    private final static double MULTIPLIER = 100.0,
				TINY = 1e-8; // -10

    private double value;

/**	Construct an AReal object of zero.
 */
    public AReal() {
	value = 0.0;
    }

/**	Construct an AReal object from the string parameter.
	@param s a string representing a valid double
 */
    public AReal( String s ) {
	value = new Double( s );
    }

/**	Construct an AReal object from the double parameter.
	@param s a double
 */
    public AReal( double s ) {
	value = s;
    }

/**	@return a random AReal object between 0 and MULTIPLIER + 1
 */
    public static Arithmetic getRandom() {
	return new AReal( MULTIPLIER * ( 0.01 + rand.nextDouble() ) );
    }

/**	@return the absolute value of this AReal object
 */
    public Arithmetic abs() {
	return new AReal( Math.abs( value ) );
    }

/**	Compute the sum of this AReal object and right.
	@param right the right operand for addition
	@return the sum of this AReal object and right
 */
    public Arithmetic add( Arithmetic right ) {
	return new AReal( value + ( ( AReal )right ).value );
    }

/**	@return the negation of this AReal
 */
    public Arithmetic negate() {
        return new AReal( -value );
    }

/**	Compute the product of this AReal object and right.
	@param right the right operand for multiplication
	@return the product of this AReal object and right
 */
    public Arithmetic multiply( Arithmetic right ) {
	return new AReal( value * ( ( AReal )right ).value );
    }

/**	@return the multiplicative inverse of this AReal object
 */
    public Arithmetic invert() {
	return new AReal( 1.0 / value );
    }

/**	Compare this AReal object to right.
	@param right the right operand for comparison
	@return -1 if the difference of value and right.value < TINY,
	+1 if difference > TINY and 0 otherwise
 */
    public int compareTo( Arithmetic right ) {
	int ans = 0;
	double diff = value - ( ( AReal )right ).value;

	if ( diff > TINY )
	    ans = 1;
	else if ( diff < TINY )
	    ans = -1;

	return ans;
    }

/**	@return a String representation of this AReal object
? format
String.format( "%f", value )
 */
    public String toString() {
	return "" + value;
    }

/**	@return true if value equals zero and false if non zero
 */
    public boolean isZero() {
	return Math.abs( value ) < TINY;
    }

/**	@return a deep clone of this AReal object
 */
    public Arithmetic clone() {
	AReal cln = ( AReal )super.clone();
	cln.value = value;

	return cln;
    }
}
