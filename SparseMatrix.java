/**
 * @author codyblack
 *
 */
import java.util.Random;

public class SparseMatrix<E extends Arithmetic> {
	
	private int rows;
	private int cols;
	private int totalElements = 0;
	private static final int DEFAULT_ROWS = 10;
	private static final int DEFAULT_COLS = 10;
	private static final int RANDOM_MAX = 1000;  //subject to change. Max value for number of random values in 3rd constructor.
	private OrderedList sparse_matrix_list;
	private EnumArithmetic kind;
	
	Random rand = new Random();
	
	/**Constructor for default SparseMatrix with 100 elements.
	 * @param kind Arithmetic type for SparseMatrix.
	 */
	public SparseMatrix(EnumArithmetic kind){
		rows = DEFAULT_ROWS;
		cols = DEFAULT_COLS;
		sparse_matrix_list = new OrderedList(kind);
		totalElements = 100;
	}
	
	/** Constructor for SparseMatrix of specified number of rows and columns.
	 * @param r Number of rows for SparseMatrix.
	 * @param c Number of columns for SparseMatrix.
	 * @param kind Arithmetic type for SparseMatrix.
	 */
	public SparseMatrix(int r, int c, EnumArithmetic kind){
		rows = r;
		cols = c;
		sparse_matrix_list = new OrderedList(kind);
		totalElements = r * c;
	}
	
	
	/**Constructor for a random sized SparseMatrix.
	 * @param isRandom Enter true to get a randomized SparseMatrix.
	 * @param kind Arithmetic type for SparseMatrix.
	 */
	public SparseMatrix(boolean isRandom, EnumArithmetic kind){
		if(isRandom==true){
			rows = rand.nextInt(RANDOM_MAX);
			cols = rand.nextInt(RANDOM_MAX);
			sparse_matrix_list = new OrderedList(kind);
			totalElements = rows * cols;
			int nonZeros = (int) (0.15 * totalElements);
			for(int i = 0; i < nonZeros; i++){
				Triple<Arithmetic> t = new Triple<Arithmetic>(true,kind,rows,cols);
				sparse_matrix_list.insertTriple(t);
			}
		}
		else{
			System.out.println("Error with constructor. Enter correct parameters.");
		}
	}
	
	/** Returns a clone of the OrderedList in this SparseMatrix.
	 * @return A clone of the sparse_matrix_list in this SparseMatrix.
	 */
	public OrderedList getSparseMatrixOrderedList(){
		return sparse_matrix_list.getOrderedList();
	}
	
	/**Getter for SparseMatrix rows.
	 * @return Number of rows in this SparseMatrix.
	 */
	public int getRows(){
		return rows;
	}
	
	/**Getter for SparseMatrix columns.
	 * @return Number of columns in this SparseMatrix.
	 */
	public int getCols(){
		return cols;
	}
	/** Add this SparseMatrix to input SparseMatrix.
	 * @param m Input SparseMatrix for addition.
	 */
	public void addSparseMatrix(SparseMatrix<Arithmetic> m){
		int index = 0;
		while(m.sparse_matrix_list.getNextTriple(index) != null){
			this.sparse_matrix_list.insertTriple(m.getSparseMatrixOrderedList().getTripleAtIndex(index));
			index++;
		}
		
	}

	/**Subtract input SparseMatrix from this SparseMatrix.
	 * @param m Input SparseMatrix for subtraction.
	 */
	public void subtractSparseMatrix(SparseMatrix<Arithmetic> m){
		m.sparse_matrix_list.negate();
		this.addSparseMatrix(m);
		}
	
	
	/**Multiply this SparseMatrix by the Arithmetic parameter.
	 * @param v Arithmetic value to be multiplied by.
	 */
	public void multiplyByArithmeticValue(Arithmetic v){
		int cond = sparse_matrix_list.getOrderedListSize();
		for(int i = 0; i < cond; i++){
			sparse_matrix_list.getTripleAtIndex(i).getValue().multiply(v);
		}
	}
	/**
	Multiples a Sparse Matrix by another Sparse Matrix
	@param m is a SparseMatrix that will be used to multiple the first SparseMatrix by.
	@return answer will return the results of the multiplication of the SparseMatrix
	*/
	public SparseMatrix<Arithmetic> multiplyBySparseMatrix(SparseMatrix<Arithmetic> m){
	//find this.col where it equals m.row
	//this.row and m.col are the location this.value times m.value is the result
		SparseMatrix<Arithmetic> answer = new SparseMatrix<Arithmetic>(this.rows, this.cols, this.kind);
		if(this.kind == m.kind){
			for(int i = 0; i<sparse_matrix_list.getOrderedListSize(); i++){
				if(this.sparse_matrix_list.getTripleAtIndex(i).getColNum() == m.sparse_matrix_list.getTripleAtIndex(i).getRowNum()){
					int k=i;
					while(m.rows == this.cols){
						Triple<Arithmetic> t = new Triple<Arithmetic>( this.sparse_matrix_list.getTripleAtIndex(i).getValue().multiply(m.sparse_matrix_list.getTripleAtIndex(k).getValue()),this.sparse_matrix_list.getTripleAtIndex(i).getRowNum(), m.sparse_matrix_list.getTripleAtIndex(k).getColNum(),this.kind);
						answer.sparse_matrix_list.insertTriple( t );
						k++;
					}			
				}	
			}
			return answer;
		}
		return null;
	}
	
	/**Transpose this SparseMatrix.
	 * @return The new transposed SparseMatrix.
	 */
	public SparseMatrix<Arithmetic> transposeSparseMatrix(){
		int i = 0;
		SparseMatrix<Arithmetic> newMatrix = new SparseMatrix<Arithmetic>(this.cols,this.rows,this.kind);
		Triple<Arithmetic> t = sparse_matrix_list.getTripleAtIndex(i);
		while(t != null){
			t.transposeTriple();
			newMatrix.sparse_matrix_list.insertTriple(t);
			t = sparse_matrix_list.getNextTriple(i);
			i++;
		}
		return newMatrix;
	}
	
	/** Computes a matrix to a positive power.
	@param power is a integer that is used to raise the Sparse Matrix to a power.
	@return answer is the new Sparse Matrix raised to the x power.
	
	*/
	public SparseMatrix<Arithmetic> posExponentiation(int power){
		SparseMatrix<Arithmetic> answer = new SparseMatrix<Arithmetic>(this.rows,this.cols,this.kind);
		SparseMatrix<Arithmetic> one = new SparseMatrix<Arithmetic>(this.rows,this.cols,this.kind);
		one.sparse_matrix_list = this.getSparseMatrixOrderedList();
		int pwr = power;
		while(pwr > 0){
			if( pwr % 2 == 1){
				if(pwr == power){
					answer.sparse_matrix_list = one.getSparseMatrixOrderedList();
				}
				else {
					answer.multiplyBySparseMatrix(one);
				}
			}
			pwr=pwr/2;
		}
		return answer;
	}
}

	
