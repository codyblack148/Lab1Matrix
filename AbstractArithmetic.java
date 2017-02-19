import java.util.Random;

/**	The class AbstractArithmetic defines the methods
 	subtract(), divide(), power(), equals() and clone() 
	of the interface Arithmetic in terms of the other operations.
	Each concrete subclass must provide attributes and constructors
	and implement the other operations of the interface Arithmetic.
	Each must also override the static method Arithmetic getRandom().
 */
public abstract class AbstractArithmetic implements Arithmetic {
    protected static Random rand = new Random();

/**	@param right the right operand for subtraction
	@return the difference of this Arithmetic object and right
 */
    public Arithmetic subtract( Arithmetic right ) {
	return add( right.negate() );
    }

/**	@param right the right operand for division
	@return the quotient of this Arithmetic object and right
 */
    public Arithmetic divide( Arithmetic right ) {
	return ( right.isZero() ? null : multiply( right.invert() ) );
    }

/**	Compute a positive integer power n of this Arithmetic object with
	an efficient lg( 2 ) algorithm.
	@param n a positive integer exponent
	@return the power of this Arithmetic object raised to the positive 
	integer n
 */
    public Arithmetic pow( int n ) {
        Arithmetic pwr = null,
                   twoPwr = ( AbstractArithmetic )clone();

        while ( n > 0 ) {
            if ( ( n % 2 ) == 1 )
		if ( pwr == null )
		    pwr = ( AbstractArithmetic )twoPwr.clone();
		else
                    pwr = pwr.multiply( twoPwr );
            twoPwr = twoPwr.multiply( twoPwr );
            n /= 2;
        }

        return pwr;
    }

/**	@return a deep clone of this Arithmetic object
 */
    public Arithmetic clone() {
	AbstractArithmetic ans = null;

	try {
	    ans = ( AbstractArithmetic )super.clone();
	} catch( CloneNotSupportedException cne ) {}

	return ans;
    }

    @Override
/**	@return true if this Arithmetic object equals the parameter or false
	if not equal
 */
    public boolean equals( Object right ) {
	return ( compareTo( ( Arithmetic )right ) == 0 );
    }

/**	@return a null Arithmetic element  
 */
    public static Arithmetic getRandom() {
	return null;
    }

/**	@return the absolute value of this Arithmetic object
     public abstract Arithmetic abs();
 */

/**	@param right the right operand for addition
	@return the sum of this Arithmetic object and right
      public abstract Arithmetic add( Arithmetic right );
 */

/**	@return the negative of this Arithmetic object
       public abstract Arithmetic negate();
 */

/**	@param right the right operand for multiplication
	@return the product of this Arithmetic object and right
      public abstract Arithmetic multiply( Arithmetic right );
 */

/**     Compute the quotient and the remainder for this / right.
	@return an array of two Arithmetic objects quotient and remainder
	of this / right where 0 <= remainder < abs( right )
    public abstract Arithmetic [] divideAndRemainder( Arithmetic right );
 */

/**	@return the inverse of this Arithmetic object
      public abstract Arithmetic invert();
 */

/**	@return true if this Arithmetic object equals zero or false if nonzero
       public abstract boolean isZero();
 */

/**	@return a String representation of this Arithmetic object
       public abstract String toString();
 */
}
