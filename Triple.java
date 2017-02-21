
/**
 * @author codyblack
 * Triple will be used to represent an element of a SparseMatrix with row number, column number, and value. 
 * @param <E> E will be used for Arithmetic types.
 */

import java.util.Random;

public class Triple<E extends Arithmetic> implements Cloneable, Comparable<Triple<Arithmetic>> {
	private Arithmetic value;
	private int row_num;
	private int col_num;
	private EnumArithmetic kind;
	
	Random rand = new Random();

	/**Triple Constructor.
	 * @param value Arithmetic value of Triple.
	 * @param row_num Row number in Sparsematrix
	 * @param col_num Column number in SparseMatrix
	 * @param kind Arithmetic type
	 */
	public Triple(Arithmetic value, int row_num,int col_num, EnumArithmetic kind){
		this.value=value;
		this.row_num=row_num;
		this.col_num=col_num;
		this.kind=kind;
	}
	
	/** Random Triple constructor.
	 * @param kind Arithmetic type of random Triple.
	 * @param rows The max number row that the Triple is allowed to have.
	 * @param cols The max number column that the Triple is allowed to have.
	 */
	public Triple(boolean isRandom, EnumArithmetic kind, int rows, int cols){
		if(isRandom == true){
			row_num = rand.nextInt(rows);
			col_num = rand.nextInt(cols);
			this.kind = kind;
			if(this.kind.equals(EnumArithmetic.INTEGER)){
				value =  AInteger.getRandom();		
			}
			else if(this.kind.equals(EnumArithmetic.PRIME)){
				value =  APrime.getRandom();
			}
			else if(this.kind.equals(EnumArithmetic.RATIONAL)){
				value =  AReal.getRandom();
			}
			else{
				value = ARational.getRandom();
			}
		}
		else{
			System.out.println("Error with constructor. Enter correct parameters.");
		}
	}
	
	/**Returns clone of Triple.
	 * @return Clone of Triple object.
	 * @throws CloneNotSupportedException
	 */
	public Triple<Arithmetic> getTriple() throws CloneNotSupportedException{
		Triple<Arithmetic> ans = null;
		try{
			ans = (Triple<Arithmetic>)super.clone();
			if(this.value != null){
				ans.value=this.value.clone();
				ans.row_num=this.row_num;
				ans.col_num=this.col_num;
				ans.kind=this.kind;
			}
		}
		catch (CloneNotSupportedException cns ) {}
		
		return ans;
	}
	/**
	 * @return The type of Arithmetic.
	 */
	public EnumArithmetic getKind(){
		return kind;
	}
	
	/**
	 * @return Row Number
	 */
	public int getRowNum(){
		return row_num;
	}
	/**
	 * @return Column Number
	 */
	public int getColNum(){
		return col_num;
	}
	/**
	 * @return Arithmetic value.
	 */
	public Arithmetic getValue(){
		return value;
	}
	/**Change the Arithmetic value in this Triple.
	 * @param value The Arithmetic value that will replace current Arithmetic value.
	 */
	public void changeTripleValue(Arithmetic value){
		this.value=value;
	}
	/**Change the row number and column number of this Triple.
	 * @param row_num Row number that will replace current row number.
	 * @param col_num Column number that will replace current column number.
	 */
	public void changeTripleLocation(int row_num,int col_num){
		this.row_num=row_num;
		this.col_num=col_num;
	}
	/**Switch the row and column number of this Triple.
	 * @return Transposed Triple
	 */
	public Triple<Arithmetic> transposeTriple(){
		Triple<Arithmetic> t = new Triple<Arithmetic>(this.value,this.col_num,this.row_num,this.kind);
				return t;
	}
	/**Tell if two Triples are equal.
	 * @param t Triple that is to be compared to this.
	 * @return Boolean to tell if the two Triples are equal or not.
	 */
	public boolean equals(Triple<Arithmetic> t){
		return (this.row_num==t.row_num && this.col_num==t.col_num && this.kind.equals(t.kind) && this.value.equals(t.value));
	}

	/**Add two Triples.
	 * @param t
	 */
	public void addTriple(Triple<Arithmetic> t){
		if(this.kind.equals(t.kind)){
			this.changeTripleValue(this.value.add(t.value));
		}
		else{
			System.out.println("The two values are not of the same type");
		}
	}
	/**Subtract t from this.
	 * @param t
	 */
	public void subtractTriple(Triple <Arithmetic> t){
		if(this.kind.equals(t.kind)){
			this.changeTripleValue(this.value.subtract(t.value));
		}
		else{
			System.out.println("The two values are not of the same type");
		}
	}
	
	/**
	 * @param t Triple to be compared to this.
	 * @return True if Triples have same row and column number and are of the same Arithmetic type.
	 */
	public boolean isEqualLocation(Triple<Arithmetic> t){
		return this.row_num==t.row_num && this.col_num==t.col_num && this.kind.equals(t.kind);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "Row number: "+row_num+" Column number: "+col_num+" Value: "+value+" Type: "+kind;
	}
	
	/**Compare to method for Triples.
	 * @param t Triple to be compared with this.
	 * @return -1,0,or 1 depending on row and column numbers
	 */
	public int compareTo(Triple<Arithmetic> t) {
		final int BEFORE = -1;
	    final int EQUAL = 0;
	    final int AFTER = 1;
	    
		if(this.row_num < t.row_num){
			return AFTER;
		}
		else if(this.row_num > t.row_num){
			return BEFORE;
		}
		else{
			//The row numbers are equal, compare column numbers now.
			if(this.col_num < t.col_num){
				return AFTER;
			}
			else if(this.col_num > t.col_num){
				return BEFORE;
			}
			else{
				return EQUAL;
			}
		}
	}
	
}
