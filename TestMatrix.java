/**	The class Main constructs a MatrixTest object.
 
class Main {
    private static final String	QA = 
// "5 5 0 1 1 1 0 1/4 1 2 3/4 2 1 1/2 2 3 1/2 3 2 3/4 3 4 1/4 4 3 1",
"3 3 0 0 1/2 0 1 1/4 0 2 1/4 1 0 1/2 1 1 0 1 2 1/2 2 0 1/4 2 1 1/4 2 2 1/2",
// overflow at power 16 
				RA = 
"5 5 0 1 1 1 0 .25 1 2 .75 2 1 .5 2 3 .5 3 2 .75 3 4 .25 4 3 1";
//"4 4 0 0 1 1 0 .2 1 1 .3 1 2 .1 1 3 .4 2 1 .5 2 2 .3 2 3 .2 3 1 .1 3 2 .6 3 3 .3";
//    "3 3 0 0 .1 0 1 .5 0 2 .4 1 0 .3 1 1 .2 1 2 .5 2 0 .7 2 1 .1 2 2 .2";
//     "3 3 0 0 .5 0 1 .2 0 2 .3 1 0 .7 1 1 .1 1 2 .2 2 0 .4 2 1 .1 2 2 .5";
//    "3 3 0 0 0.9 0 1 0.1 0 2 0 1 0 .15 1 1 .6 1 2 0.25 2 0 0 2 1 .15 2 2 .85";
//    "3 3 0 0 0.5 0 1 .25 0 2 .25 1 0 0.5 1 1 0 1 2 0.5 2 0 .25 2 1 .25 2 2 0.5";

    public static void main( String [] args ) {
	// new TestMatrix();
	new TestMatrix( QA, EnumArithmetic.RATIONAL );
	// new TestMatrix( RA, EnumArithmetic.REAL );
	// new TestMatrix( RA, EnumArithmetic.REAL, true );
	// new TestMatrix( QA, EnumArithmetic.RATIONAL, true );
    }
}

/**	The class MatrixTest exercises some of the methods of 
 	Matrix< Arithmetic > classes.
 */

public class TestMatrix {
    private static final String	IA = "4 4 0 0 1 0 3 -1 1 0 2 2 3 5 3 2 -6",
		IB = "4 4 0 0 -1 0 2 1 1 1 -1 2 3 -2 3 3 4",
		RA = "4 4 0 0 -0.1 0 2 1.5 1 1 -1. 2 3 -2. 3 3 4.5",
		RB = "4 4 0 0 -1.3 0 1 1. 0 1 -1. 2 2 -2.5 3 3 4.",
		QA = "3 3 0 0 -1/3 0 2 1/5 1 1 2/1 2 0 -2/3 2 2 4/5",
		QB = "3 3 0 0 1/2 0 2 1/3 2 1 -2/3 2 2 4/1";

/**	Construct a TestMatrix object.
 */
    public TestMatrix() {
	Matrix< Arithmetic >
	ra = new Matrix< Arithmetic >( 4, 4, 9, EnumArithmetic.RATIONAL ),
	rb = new Matrix< Arithmetic >( 4, 4, 8, EnumArithmetic.RATIONAL );

	test( ra, rb, EnumArithmetic.RATIONAL );

	ra = new Matrix< Arithmetic >( RA, EnumArithmetic.REAL );
	rb = new Matrix< Arithmetic >( RB, EnumArithmetic.REAL );
	test( ra, rb, EnumArithmetic.REAL );
	ra = new Matrix< Arithmetic >( 3, 3, 9, EnumArithmetic.REAL );
	rb = new Matrix< Arithmetic >( 3, 3, 9, EnumArithmetic.REAL );
	test( ra, rb, EnumArithmetic.REAL );

	ra = new Matrix< Arithmetic >( 5, 5, 20, EnumArithmetic.INTEGER );
	rb = new Matrix< Arithmetic >( 5, 5, 25, EnumArithmetic.INTEGER );
	test( ra, rb, EnumArithmetic.INTEGER );

	APrime.setPrime( 23 );
	ra = new Matrix< Arithmetic >( 3, 3, 9, EnumArithmetic.PRIME );
	rb = new Matrix< Arithmetic >( 3, 3, 9, EnumArithmetic.PRIME );
	test( ra, rb, EnumArithmetic.PRIME );

    }

/**	Test some of the methods of the Matrix class in kind on matrices
 	ra and rb.
	@param ra a Matrix
	@param rb another Matrix
	@param kind the class of the items in the matrices
 */
    private void test( Matrix< Arithmetic > ra, Matrix< Arithmetic > rb,
			EnumArithmetic kind ) {
	System.out.println( "\nTest matrix with values from " + kind );
	Arithmetic three = null;
	if ( kind.equals( EnumArithmetic.RATIONAL ) )
	    three = new ARational( 3 );
	else if ( kind.equals( EnumArithmetic.PRIME ) )
	    three = new APrime( 3 );
	else if ( kind.equals( EnumArithmetic.REAL ) )
	    three = new AReal( 3.0 );
	else if ( kind.equals( EnumArithmetic.INTEGER ) )
	    three = new AInteger( 3 );

	Matrix< Arithmetic >
		sum = ra.add( rb ),
		diff = ra.subtract( rb ),
		prod = ra.multiply( rb ),
		pwr = ra.pow( 4 ),
		p4 = ra.multiply( ra ).multiply( ra ).multiply( ra ),
		prod3 = prod.multiply( three ),
		diffSqd = diff.pow( 2 ),
		diff2 = ra.multiply( ra ).subtract( prod ).
			subtract( rb.multiply( ra ) ).
			add( rb.multiply( rb ) );
	
	System.out.println( "A\n" + ra  + "\nB\n" + rb + "\nsum\n" + sum + 
		"\nA^4\n" + pwr +
		"\nA*A*A*A\n" + p4  + "\nA^4 == A*A*A*A ? " + pwr.equals( p4 ) +
		"\nA*A*A*A - pwr\n" + p4.subtract( pwr ) );
	System.out.println( "A\n" + ra + "\nB\n" + rb + "\ndiff\n" + diff +
		"\nprod\n" + prod + "\n3*prod\n" + prod3 + 
		"\n( a - b )^2\n" + diffSqd + 
		"\na^2 - ab - ba + b^2\n" + diff2 + "\nsquared == binTh ? " +
	diffSqd.equals( diff2 ) + "\n( a - b )^2 - ( a^2 - ab - ba + b^2 )\n" + 
		diffSqd.subtract( diff2 ) ); 
	// The product of matrices is not commutative!
    }

    public TestMatrix( String s, EnumArithmetic kind ) {
	Matrix< Arithmetic > qm = new Matrix< Arithmetic >( s, kind ),
			     pwr = qm, ppwr;
	boolean same = false;
	System.out.println( qm );
	for ( int i = 1; ( i < 40 ) && ! same; i++ ) {
	    ppwr = pwr.multiply( qm );
	    System.out.println( "" + ( i + 1 ) + "\n" + ppwr );
	    same = ppwr.equals( pwr );
	    // if ( i == 39 )
		System.out.println( "\n" + ppwr.subtract( pwr ) + "\n" );
	    pwr = ppwr;
 	}
    }

    public TestMatrix( String s, EnumArithmetic kind, boolean tr ) {
	Matrix< Arithmetic > qm = new Matrix< Arithmetic >( s, kind ),
			     tqm = qm.transpose(),
			     prod = qm.multiply( tqm ),
			     prd = tqm.multiply( qm );

	System.out.println( qm + "\ntranspose\n" + tqm + "\nqm * tqm\n" + prod +
			    "\ntqm * qm\n" + prd );
    }
}
