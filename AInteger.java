/**	The class AInteger manages arithmetic with integers.
 */

public class AInteger extends AbstractArithmetic {
    private final static int TOP_INT = 100;

    private int	value;

/**	Construct an AInteger object of zero.
 */
    public AInteger() {
	value = 0;
    }

/**	Construct an AInteger object from the parameter.
 	@param s a String object 
	@precondition s consists of + or - followed by digits
 */
    public AInteger( String s ) {
	value = new Integer( s );
    }

/**	Construct an AInteger object from the parameter.
	@param s a Java int
 */
    public AInteger( int s ) {
	value = s;
    }

/**	@return a random AInteger object between 1 and TOP_INT
 */
    public static Arithmetic getRandom() {
	return new AInteger( 1 + rand.nextInt( TOP_INT ) );
    }

/**	@return true if value is zero or false if non-zero
 */
    public boolean isZero() {
	return value == 0;
    }

/**	@return the absolute value of this AInteger object
 */
    public Arithmetic abs() {
	return new AInteger( Math.abs( value ) );
    }

/**	Compute the sum of this AInteger object and right.
	@param right the right operand for addition
	@return the sum of this AInteger object and right
 */
    public Arithmetic add( Arithmetic right ) {
	return new AInteger( value + ( ( AInteger )right ).value );
    }

/**	Negate this AInteger object.
	@return the negation of this AInteger object
 */
    public Arithmetic negate() {
        return new AInteger( -value );
    }

/**	Compute the product of this AInteger object and right.
	@param right the right operand for multiplication
	@return the product of this AInteger object and right
 */
    public Arithmetic multiply( Arithmetic right ) {
	return new AInteger( value * ( ( AInteger )right ).value );
    }

/**	Compute the multiplicative inverse of this AInteger object.
	@return null except for 1 or -1

	The multiplicative inverse of an integer does not exist except for 
	1 or -1.
 */
    public Arithmetic invert() {
	return ( Math.abs( value ) == 1 ) ? new AInteger( value ) : null;
    }

    @Override
/**	Divide this AInteger object by right.
	@param right the right operand for division 
	@return the quotient this / right
 */
    public Arithmetic divide( Arithmetic right ) {
	return new AInteger( value / ( ( AInteger )right ).value );
    }

/**     Compute the quotient and the remainder for this / right.
	@return an array of two Arithmetic objects quotient and remainder
	of this / right where 0 <= remainder < abs( right )
    public Arithmetic [] divideAndRemainder( Arithmetic right ) {
    }
 */

/**	Compare this AInteger object to the parameter.
	@param right the right operand for comparison
	@return a negative integer if this AInteger object < right
	zero if this equals right and a positive integer if this > right
 */
    public int compareTo( Arithmetic right ) {
	return value - ( ( AInteger ) right ).value;
    }

/**	@return a String representation of this AInteger object
 */
    public String toString() {
	return "" + value;
    }

/**	@return a deep clone of this AInteger object
 */
    public Arithmetic clone() {
	AInteger cln = ( AInteger )super.clone();
	cln.value = value;

	return cln;
    }
}
