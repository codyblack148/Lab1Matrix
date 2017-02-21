// ? rows cols as attributes
// ? class TriDiagonal
// ? class Prob

/*	File:	Matrix.java
	The class Matrix manages operations on a two dimensional array 
	of Arithmetic elements.
	@author yanushka
 */

import java.util.Random;

class Matrix< E extends Arithmetic > implements Cloneable {
    private static final String	SPACE_REGEX = "\\s+",
				SLASH = "/",
				POINT = ".";
    private static final int ROWS_DEFAULT = 10,
			     COLS_DEFAULT = 10;
    private int rows;
    private int cols;
    private static Random rand = new Random();
    private Arithmetic [][] ary;

/**	Construct a Matrix object with a default number of rows and cols,
	filled with nulls.
 */
    public Matrix() {
	this( ROWS_DEFAULT, COLS_DEFAULT );
    }

/**	Construct a Matrix object of a particular dimension given by the 
	parameters filled with nulls.
	@param rows the positive integer number of rows
	@param cols the positive integer number of columns
 */
    public Matrix( int rows, int cols ) {
	ary = new Arithmetic[ rows ][ cols ];
	this.rows = rows;
	this.cols = cols;
    }

/**	Construct a Matrix object from a formatted string of the form
	    #rows #cols row col value ...
	@param s a String object of two positive integers for the number of
	rows and columns followed by triples of the form row column value
	@param kind an EnumArithmetic object
 */
    public Matrix( String s, EnumArithmetic kind ) {
	String [] tokens = s.split( SPACE_REGEX );

	ary = new Arithmetic[ new Integer( tokens[ 0 ] ) ]
			    [ new Integer( tokens[ 1 ] ) ];

	for ( int i = 2; i < tokens.length; i +=3 ) {
	    int row = new Integer( tokens[ i ] ),
	        col = new Integer( tokens[ i + 1 ] );
	    if ( kind.equals( EnumArithmetic.RATIONAL ) )
		ary[ row ][ col ] = new ARational( tokens[ i + 2 ] );
	    else if ( kind.equals( EnumArithmetic.REAL ) )
		ary[ row ][ col ] = new AReal( tokens[ i + 2 ] );
	    else if ( kind.equals( EnumArithmetic.PRIME ) )
	        ary[ row ][ col ] = new APrime( tokens[ i + 2 ] );
	    else if ( kind.equals( EnumArithmetic.INTEGER ) )
	        ary[ row ][ col ] = new AInteger( tokens[ i + 2 ] );
	}
    }

/**     Construct a matrix with random terms from the class that kind 
	determines.
 	@param r a positive integer for the number of rows
 	@param c a positive integer for the number of columns
	@param numberTerms a positive integer for the number of non zero terms
	@param kind an EnumArithmetic object
	if numberTerms < r * c 
            do numberTerm times
                Put a new random instance of indicated Arithmetic class.
		    in a random row and random column.
	else
	    for each row and each column
                Put a new random instance of indicated Arithmetic class there.
 */
    public Matrix( int r, int c, int numberTerms, EnumArithmetic kind ) {
	ary = new Arithmetic[ r ][ c ];

	if ( numberTerms < r * c ) {
            for ( int i = 0; i < numberTerms; i++ ) {
	        int row = rand.nextInt( r ),
	            col = rand.nextInt( c );
	        Arithmetic value = null;
	        if ( kind.equals( EnumArithmetic.REAL ) )
	            value = AReal.getRandom();
	        else if ( kind.equals( EnumArithmetic.RATIONAL ) )
	            value = ARational.getRandom();
	        else if ( kind.equals( EnumArithmetic.PRIME ) )
                    value = APrime.getRandom();
	        else if ( kind.equals( EnumArithmetic.INTEGER ) )
	            value = AInteger.getRandom();
	        ary[ row ][ col ] = value;
            }
	} else {
	    for ( int i = 0; i < r; i++ )
		for ( int j = 0; j < c; j++ ) {
	            Arithmetic value = null;
	            if ( kind.equals( EnumArithmetic.REAL ) )
	                value = AReal.getRandom();
	            else if ( kind.equals( EnumArithmetic.RATIONAL ) )
	                value = ARational.getRandom();
	            else if ( kind.equals( EnumArithmetic.PRIME ) )
                        value = APrime.getRandom();
	            else if ( kind.equals( EnumArithmetic.INTEGER ) )
	                value = AInteger.getRandom();
	            ary[ i ][ j ] = value;
	        }
	}
    }

/**	Add this Matrix object to the parameter.
	@param right the right operand for addition
	@return the sum as this + right
	@precondition dimensions of the parameter agree with current dimensions.
 */
    public Matrix< Arithmetic > add( Matrix< Arithmetic > right ) {
	int rows = Math.max( ary.length, right.ary.length ),
	    cols = Math.max( ary[ 0 ].length, right.ary[ 0 ].length );
	Matrix< Arithmetic > sum = new Matrix< Arithmetic >( rows, cols );

	for ( int i = 0; i < rows; i++ ) {
	    Arithmetic [] row = getRow( i ),
			  rRow = right.getRow( i ),
			  sumRow = addRows( row, rRow );
	    System.arraycopy( sumRow, 0, sum.ary[ i ], 0, sumRow.length );
	}

	return sum;
    }

/**	Add the one dimensional parameters of the same length.
 	@param left an Arithmetic array
 	@param right another Arithmetic array of the same length as left
	@return the component wise sum of the two arrays
	@precondition the lengths of left and right are equal
 */
    private Arithmetic [] addRows( Arithmetic [] left, Arithmetic [] right ) {
	Arithmetic [] sum = new Arithmetic[ left.length ];
	for ( int i = 0; i < left.length; i++ )
	    if ( ( left[ i ] != null ) && ( right[ i ] != null ) ) {
		sum[ i ] = left[ i ].add( right[ i ] );
		if ( sum[ i ].isZero() )
		    sum[ i ] = null;
	    } else if ( left[ i ] != null )
		sum[ i ] = left[ i ].clone();
	    else if ( right[ i ] != null )
		sum[ i ] = right[ i ].clone();

	return sum;
    }

/**	Subtract the parameter from this Matrix object.
	@param right the right operand for subtraction
	@return the sum as this - right
 */
    public Matrix< Arithmetic > subtract( Matrix< Arithmetic > right ) {
	return add( right.negate() );
    }

/**	@return the negative of this Matrix object
 */
    public Matrix< Arithmetic > negate() {
	int rows = ary.length,
	    cols = ary[ 0 ].length;
	Matrix< Arithmetic > neg = new Matrix< Arithmetic >( rows, cols );

	for ( int i = 0; i < rows; i++ )
	    for ( int j = 0; j < cols; j++ )
		if ( ary[ i ][ j ] != null )
		    neg.ary[ i ][ j ] = ary[ i ][ j ].negate();

	return neg;
    }

/**	Decide whether the parameters are valid row and column indices for
	this Matrix object.
	@param i a row index
	@param j a column index
	@return true if 0 <= i < number of rows and 0 <= j < number of columns
 */
    private boolean inRange( int i, int j ) {
	return ( 0 <= i ) && ( i < ary.length ) && 
	       ( 0 <= j ) && ( j < ary[ 0 ].length );
    }

/**	Multiply this matrix by the constant named factor.
	@param factor an Arithmetic value that multiplies each entry 
	@return factor times this Matrix object
 */
    public Matrix< Arithmetic > multiply( Arithmetic factor ) {
	int rows = ary.length,
	    cols = ary[ 0 ].length;
	Matrix< Arithmetic > ans = new Matrix< Arithmetic >( rows, cols );

	for ( int i = 0; i < rows; i++ ) 
	    for ( int j = 0; j < cols; j++ ) 
		if ( ary[ i ][ j ] != null )
		    ans.ary[ i ][ j ] = ary[ i ][ j ].multiply( factor );

	return ans;
    }

/**	Multiply this Matrix object and the parameter.
	@param right the right operand for multiplication
	@return the product as this * right
	@precondition the number of columns of ary equals the number of rows
	of right.ary
 */
    public Matrix< Arithmetic > multiply( Matrix< Arithmetic > right ) {
	int rows = ary.length,
	    cols = right.ary[ 0 ].length,
	    mid = Math.min( ary[ 0 ].length, right.ary.length );
	Matrix< Arithmetic > ans = new Matrix< Arithmetic >( rows, cols );

	for ( int i = 0; i < rows; i++ ) {
	    Arithmetic [] rowI = getRow( i );
	    for ( int j = 0; j < cols; j++ ) {
		Arithmetic [] colJ = right.getCol( j );
		ans.ary[ i ][ j ] = dotProduct( rowI, colJ );
	    }
	}

	return ans;
    }

/**	@param r a non negative row index into ary
	@return row r of the ary attribute of this Matrix object
 */
    private Arithmetic [] getRow( int r ) {
	int cols = ary[ 0 ].length;
	Arithmetic [] row = new Arithmetic[ cols ];
	System.arraycopy( ary[ r ], 0, row, 0, cols );

	return row;
    }

/**	@param c a non negative column index into ary
	@return column c of the ary attribute of this Matrix object
 */
    private Arithmetic [] getCol( int c ) {
	int rows = ary.length;
	Arithmetic [] col = new Arithmetic[ rows ];

	for ( int r = 0; r < rows; r++ )
	    col [ r ] = ary[ r ][ c ];

	return col;
    }

/**	Compute the dot product of two vectors of the same size.
 	@param row a 1D Arithmetic array
 	@param col another 1D Arithmetic array
	@return the sum of the component wise products of the elements of 
	row and col
 */
    private Arithmetic dotProduct( Arithmetic [] row, Arithmetic [] col ) {
	Arithmetic sum = null;
	for ( int k = 0; k < row.length; k++ )
	    if ( ( row[ k ] != null ) && ( col[ k ] != null ) )
		if ( sum == null )
		    sum = row[ k ].multiply( col[ k ] );
		else
		    sum = sum.add( row[ k ].multiply( col[ k ] ) );
    
	return ( ( sum == null ) || sum.isZero() ? null : sum );
    }

/**	@return the transpose of this Matrix< Arithmetic > object
 */
    public Matrix< Arithmetic > transpose() {
	int cols = ary[ 0 ].length, 
	    rows = ary.length;
	Matrix< Arithmetic > tp = new Matrix< Arithmetic >( cols, rows );

	for ( int c = 0; c < cols; c++ )
	    for ( int r = 0; r < rows; r++ )
		tp.ary[ c ][ r ] = ary[ r ][ c ];

	return tp;
    }

/**	Compute the n-th power of this Matrix object with an efficient 
	lg( n ) algorithm.
	@param n a positive integer power
	@return the Matrix object this ^ n
	@precondition number of rows equals number of columns
 */
    public Matrix< Arithmetic > pow( int n ) {
        Matrix< Arithmetic > power = null, 
                             twoPwr = clone();

        while ( n > 0 ) {
            if ( n % 2 == 1 )
		if ( power == null )
		    power = twoPwr.clone();
		else
                    power = twoPwr.multiply( power );
            n /= 2;
            twoPwr = twoPwr.multiply( twoPwr );
        }

        return power;
    }

/**	@return a String representation of this Matrix object
 */
    public String toString() {
	int rows = ary.length,
	    cols = ary[ 0 ].length;
	StringBuilder sb = new StringBuilder();

	for ( int i = 0; i < rows; i++ ) {
	    for ( int j = 0; j < cols; j++ )
		if ( ( ary[ i ][ j ] != null ) && ! ary[ i ][ j ].isZero() )
		    sb.append( ary[ i ][ j ].toString() + " " );
		else
		    sb.append( "0 " );
	    sb.append( "\n" );
	}

	return sb.toString().trim();
    }

/**	@return the values of this Matrix object as a string
 */
    public String getValues() {
	return toString();
    }

/**	@param right a Matrix object for comparison
	@return true if this Matrix object equals the parameter or 
	false otherwise
 */
    public boolean equals( Matrix< E > right ) {
	boolean ans = ( ary.length == right.ary.length ) && 
		      ( ary[ 0 ].length == right.ary[ 0 ].length );

	if ( ans ) {
	    int rows = ary.length,
	        cols = ary[ 0 ].length;

	    for ( int i = 0; ans && ( i < rows ); i++ )
	        for ( int j = 0; ans && ( j < cols ); j++ ) 
		    if ( inRange( i, j ) && ( ary[ i ][ j ] == null ) && 
		         right.inRange( i, j ) && 
			 ( right.ary[ i ][ j ] != null ) ) 
		        ans = false;
		    else if ( inRange( i, j ) && ( ary[ i ][ j ] != null ) && 
			      right.inRange( i, j ) && 
			     ( right.ary[ i ][ j ] == null ) ) 
		        ans = false;
		    else if ( inRange( i, j ) && ( ary[ i ][ j ] != null ) && 
			      right.inRange( i, j ) && 
			      ( right.ary[ i ][ j ] != null ) ) 
		        // ans = ary[ i ][ j ].equals( right.ary[ i ][ j ] );
			ans = ary[ i ][ j ].subtract( right.ary[ i ][ j ] ).isZero();
	}

	return ans;
    }

/**	@return a deep clone of this Matrix object
 */
    public Matrix< Arithmetic > clone() {
	Matrix< Arithmetic > ans = null;

	try {
	    ans = ( Matrix< Arithmetic > )super.clone();
	    for ( int i = 0; i < ary.length; i++ )
		for ( int j = 0; j < ary[ 0 ].length; j++ )
		    if ( ary[ i ][ j ] != null )
		       ans.ary[ i ][ j ] = ary[ i ][ j ].clone();
	} catch( CloneNotSupportedException cns ) {}

	return ans;
    }

/**	@param r a row index
	@param c a column index
	@return the value of the entry at row r and column c.
 */
    public Arithmetic get( int r, int c ) {
	return ary[ r ][ c ];
    }

/**	@return the dimension of this Matrix object as an array of two integers
 */
    public int [] getDimension() {
	return new int [] { ary.length, ary[ 0 ].length };
    }

/**	@return the number of rows of this Matrix object
 */
    public int getRows() {
	return ary.length;
    }

/**	@return the number of columns of this Matrix object
 */
    public int getCols() {
	return ary[ 0 ].length;
    }

/**	@return ary attribute
 */
    public Arithmetic [][] getAry() {
	return ary;
    }
    
    public SparseMatrix<Arithmetic> changeToSparseMatrix(EnumArithmetic kind){
    	int count = 0;
    	int i, j;
    	for(i = 0; i <= rows; i++){
    		for(j = 0; j <= cols; j++){
				if(ary[i][j].equals(0)){
					count++;
				}
    		}
    	}	
		if((rows*cols*.85)<=count){
			SparseMatrix<Arithmetic> answer = new SparseMatrix<Arithmetic>(count,count,kind);
			for(i = 0; i <= rows; i++){
				for(j = 0; j <= cols; j++){
					if(!(ary[i][j].equals(0))){
						Triple<Arithmetic> t = new Triple<Arithmetic>(ary[i][j],i,j,kind);
						answer.getSparseMatrixOrderedList().insertTriple( t );
					}
				}
			}
			return answer;
		}
		else {
			System.out.println(" We are sorry but there is too many important values in this matrix to make a sparse matrix");
			return null;
		}
    }
}
