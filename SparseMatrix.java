
/**
 * @author codyblack
 *
 */
public class SparseMatrix<E extends Arithmetic> {
	
	private int rows;
	private int cols;
	private int totalElements = 0;
	private static final int DEFAULT_ROWS = 10;
	private static final int DEFAULT_COLS = 10;
	private static final int RANDOM_MAX = 1000;  //subject to change. Max value for number of random values in 3rd constructor.
	private OrderedList sparse_matrix_list;
	private EnumArithmetic kind;
	
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
			//rows and columns set to random number between 0 and RANDOM_MAX
			sparse_matrix_list = new OrderedList(kind);
			totalElements = rows * cols;
			/*put code here to populate this SparseMatrix with (0.15 * totalElements) number of 
			 * random Arithmetic values of type kind. May have to write a seperate method to do this.
			 */
		}
	}
	public void addSparseMatrix(SparseMatrix<Arithmetic> m){
		//I don't know how to parametarize this right now.
		//call the insertTriple method to do all the math for you
		
	}

	public void subtractSparseMatrix(SparseMatrix<Arithmetic> m){
	//use a addSparseMatrix(m.sparse_matrix_list.negate())
	}

	



}
