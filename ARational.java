/**	The class ARational does arithmetic with rational numbers.
	A rational is a pair of two long values, top and btm.

	Invariants:
	A rational is always in lowest terms, that is, the greatest
	common divisor of top and btm is one.
	The btm value is always positive.

	@author yanushka
 */

public class ARational extends AbstractArithmetic {
    public static final String	SLASH = "/";
    private final static double	MULTIPLIER = 10.0;
    private final static int 	MAX_RAT = 25;

    private long top, 
		 btm;

/**	Construct an ARational object of zero.
 */
    public ARational() {
	top = 0;
	btm = 1;
    }

/**	Construct a ARational object from a formatted string parameter.
	@param s a string of the form topLong / bottomLong
 */
    public ARational( String s ) {
        int ndx = s.indexOf( SLASH );

        if ( ndx < 0 ) {
            top = new Long( s.trim() );
	    btm = 1;
        } else {
            top = new Long( s.substring( 0, ndx ).trim() );
            btm = new Long( s.substring( ndx + 1 ).trim() );
        }

	reduce();
    }

/**	Construct a ARational object from a long parameter.
	@param s a long integer
 */
    public ARational( long s ) {
	top = s;
	btm = 1;
    }

/**	Construct a ARational object from a pair of long parameters.
	@param s a long integer for top
	@param t a long integer for btm
 */
    public ARational( long s, long t ) {
	top = s;
	btm = t;
	reduce();
    }

/**	Construct a ARational object from a double parameter.
	@param s a double value

	Set top to MULTIPLIER * s, rounded to a long.
	Set btm to MULTIPLIER * arccos( s ), rounded to a long.
 */
    public ARational( double s ) {
	double	tens = 10.0;

	top = Math.round( MULTIPLIER * s );
	while ( top == 0L ) {
	    tens *= MULTIPLIER;
	    top = Math.round( tens * s );
	}
	btm = Math.round( MULTIPLIER * Math.acos( s ) );
	reduce();
    }

/**	@return a random ARational object
 */
    public static Arithmetic getRandom() {
	int top = 1 + rand.nextInt( MAX_RAT ),
	    btm = 1 + rand.nextInt( MAX_RAT );

	return new ARational( top, btm );
    }

/**	@return the absolute value of this ARational object
 */
    public Arithmetic abs() {
	return new ARational( Math.abs( top ), btm );
    }

/**	Add this ARational to its parameter.
	@param right the right operand for addition
	@return the rational sum of this and right
 */
    public Arithmetic add( Arithmetic right ) {
	ARational aRight = new ARational( right.toString() );
	return new ARational( top * aRight.btm + btm * aRight.top,
				btm * aRight.btm );
    }

/**	@return the negation of this ARational object
 */
    public Arithmetic negate() {
        return new ARational( -top, btm );
    }

/**	Multiply this ARational by its parameter.
	@param right the right operand for multiplication
	@return the rational product of this and right
 */
    public Arithmetic multiply( Arithmetic right ) {
	ARational aRight = ( ARational )right;
	return new ARational( top * aRight.top, btm * aRight.btm );
    }

/**	@return the multiplicative inverse of this ARational object
 */
    public Arithmetic invert() {
	return ( isZero() ? null : new ARational( btm, top ) );
    }

/**	Compare this ARational to the parameter.
	@return the Long compareTo() of top * right.btm and btm * right.top
 */
    public int compareTo( Arithmetic right ) {
	ARational aRight = ( ARational )right;
	return new Long( top * aRight.btm ).compareTo( btm * aRight.top );
    }

/**	@return a String representation of this ARational object
 */
    public String toString() {
        return ( btm == 1 ) ? "" + top : "" + top + "/" + btm;
    }

/**	Decide whether this ARational object equals zero.
	@return true if top is zero or false otherwise
 */
    public boolean isZero() {
	return top == 0L;
    }

/**	Reduce this ARational object.
 */
    private void reduce() {
        long	g = findGCD( Math.abs( top ), Math.abs( btm ) );

	if ( g > 0 ) {
            top = top / g;
            btm = btm / g;

            if ( btm < 0 ) {
                btm *= -1;
                top *= -1;
            }
	}
    }

/**	Find the greatest common divisor of the two parameters with the
	Euclidean algorithm.
	@param a a long value
	@param b another long value
	@return the greatest common divisor of a and b
	
	@precondition a and b are positive
 */
    private long findGCD( long a, long b ) {
        long	tmp;

        while ( b > 0 ) {
            tmp = a;
            a = b;
            b = tmp % b;
        }
	
	return a;
    }

/**	@return a deep clone of this ARational object
 */
    public Arithmetic clone() {
	ARational cln = ( ARational )super.clone();
	cln.top = top;
	cln.btm = btm;

	return cln;
    }
}
