// ? a + b sqrt( prime )

/**	Define an Arithmetic interface to support operations on matrices.
 */
public interface Arithmetic extends Cloneable, Comparable< Arithmetic > {
/**	@return the absolute value of this Arithmetic object
 */
    public Arithmetic abs();

/**	Compute the sum this + right.
	@param right the right operand for addition
	@return the sum of this Arithmetic object and right
 */
    public Arithmetic add( Arithmetic right );

/**	Negate this Arithmetic object.
	@return the negative of this Arithmetic object
 */
    public Arithmetic negate();

/**	Compute the difference this - right.
	@param right the right operand for subtraction
	@return the difference of this Arithmetic object and right
 */
    public Arithmetic subtract( Arithmetic right );

/**	Compute the product this * right.
	@param right the right operand for multiplication
	@return the product of this Arithmetic object and right
 */
    public Arithmetic multiply( Arithmetic right );

/**	Compute the multiplicative inverse of this Arithmetic object.
	@return the multiplicative inverse of this Arithmetic object
 */
    public Arithmetic invert();

/**	Compute the quotient this / right.
	@param right the right operand for division
	@return the quotient of this Arithmetic object and right
 */
    public Arithmetic divide( Arithmetic right );

/**	Compute the quotient and the remainder for this / right.
 	@return an array of two Arithmetic objects quotient and remainder
	of this / right where 0 <= remainder < abs( right )
    public Arithmetic [] divideAndRemainder( Arithmetic right );
 */

/**	Compute this Arithmetic obect to the power n.
	@param n the positive integer exponent
	@return the power of this Arithmetic object raised to the positive 
	integer n
 */
    public Arithmetic pow( int n );

/**	@return true if this Arithmetic object equals zero or false if nonzero
 */
    public boolean isZero();

/**	@return a String representation of this Arithmetic object
 */
    public String toString();

/**	@return a deep clone of this Arithmetic object
 */
    public Arithmetic clone();

    @Override
/**	@return true if this Arithmetic object equals the parameter or false
	if not equal
 */
    public boolean equals( Object right );

/*	@return a random Arithmetic element  
 */
//    public static Arithmetic getRandom();
}
