/**	The class APrime manages arithmetic with integers mod a prime number.
 */
public class APrime extends AbstractArithmetic {
    private final static int PRIME_DEFAULT = 101;

    private static int prime;
    private int	value;

/**	Construct an APrime object of zero.
 */
    public APrime() {
	if ( prime == 0 )
	    prime = PRIME_DEFAULT;
	value = 0;
    }

/**	Set the prime attribute from the parameter.
 	@param pr a prime number
	@precondition pr is prime
 */
    public static void setPrime( int pr ) {
	prime = pr;
    }

/**	@return prime
 */
    public static int getPrime() {
	return prime;
    }

/**	Construct an APrime object from the string parameter.
 */
    public APrime( String s ) {
	this( new Integer( s ) );
    }

/**	Construct an APrime object from the int parameter.
	@param s an int
 */
    public APrime( int s ) {
	if ( prime == 0 )
	    prime = PRIME_DEFAULT;
	value = s % prime;
	if ( value < 0 )
	    value += prime;
    }

/**	@return a random APrime object between 1 and prime, inclusive
 */
    public static Arithmetic getRandom() {
	if ( prime == 0 )
	    prime = PRIME_DEFAULT;

	APrime ap = new APrime( rand.nextInt( prime ) );

	while ( ap.isZero() ) 
	    ap = new APrime( rand.nextInt( prime ) );

	return ap;
    }

/**	@return true if value is zero or false if non-zero
 */
    public boolean isZero() {
	return value == 0;
    }

/**	@return the absolute value of this APrime object
 */
    public Arithmetic abs() {
	return new APrime( value );
    }

/**	Compute the sum of this APrime object and right.
	@param right the right operand for addition
	@return the sum of this APrime object and right
 */
    public Arithmetic add( Arithmetic right ) {
	return new APrime( value + ( ( APrime )right ).value );
    }

/**	@return the negation of this APrime object
 */
    public Arithmetic negate() {
        return new APrime( prime - value );
    }

/**	Compute the product of this APrime object and right.
	@param right the right operand for multiplication
	@return the product of this APrime object and right
 */
    public Arithmetic multiply( Arithmetic right ) {
	return new APrime( value * ( ( APrime )right ).value );
    }

/**	@return the multiplicative inverse of this APrime object
 */
    public Arithmetic invert() {
	APrime inv = null;

	if ( value != 0 ) {
	    int [] gxy = extGCD( value, prime );
	    inv = new APrime( gxy[ 1 ] );
	}

	return inv;
    }

/**	Compare this APrime object to right.
	@param right the right operand for comparison
	@return a negative integer if this APrime object < right,
	zero if this equals right or a positive integer if this > right
 */
    public int compareTo( Arithmetic right ) {
	return ( ( APrime )subtract( right ) ).value;
    }

/**	@return a String representation of this APrime object
 */
    public String toString() {
	return "" + value;
    }

/**	@return a deep clone of this APrime object
 */
    public Arithmetic clone() {
	APrime cln = ( APrime )super.clone();
	cln.value = value;

	return cln;
    }

/**	Implement the extended Euclidean algorithm.
 	Find integers xP, yP and gP satisfying the equation
  	a * xP + b * yP = gP
  	where gP = gcd of a and b.
 */
    private int [] extGCD( int a, int b ) {
        int s = 0, s0 = 1, t = 1, t0 = 0, r = b, r0 = a, tmp, quo;

        while ( r > 0 ) {
	    quo = r0 / r;
	    tmp = r0;
	    r0 = r;
	    r = tmp - quo * r;
	    tmp = s0;
	    s0 = s;
	    s = tmp - quo * s;
	    tmp = t0;
	    t0 = t;
	    t = tmp - quo * t;
        }
    	int gP = r0,
    	    xP = ( s0 > 0 ) ? s0 : prime + s0,
    	    yP = ( t0 > 0 ) ? t0 : prime + t0;

	return new int [] { gP, xP, yP };
    }
}
