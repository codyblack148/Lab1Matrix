
/**
 * @author codyblack
 * Triple will be used to represent an element of a SparseMatrix with row number, column number, and value. 
 * @param <E> E will be used for Arithmetic types.
 */
public class Triple<E extends Arithmetic> implements Cloneable {
	private Arithmetic value;
	private int row_num;
	private int col_num;
	private EnumArithmetic kind;

	/**
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
	
	/**
	 * @return Clone of Triple object.
	 * @throws CloneNotSupportedException
	 */
	public Triple<Arithmetic> getTriple() throws CloneNotSupportedException{
		return (Triple<Arithmetic>) this.clone();
	}
	public EnumArithmetic getKind(){
		return kind;
	}
	
	public int getRowNum(){
		return row_num;
	}
	public int getColNum(){
		return col_num;
	}
	public Arithmetic getValue(){
		return value;
	}
	public void changeValue(Arithmetic value){
		this.value=value;
	}
	public void changeLocation(int row_num,int col_num){
		this.row_num=row_num;
		this.col_num=col_num;
	}
	
}
