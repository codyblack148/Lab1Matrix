import java.util.ArrayList;


/**OrderedList represents an ArrayList of Triples in sorted order.
 * @author codyblack
 *
 */
public class OrderedList implements Cloneable {
	
	/*
	 * I currently have all of the methods I think we need in here. I went ahead
	 * and wrote the easier ones but several are left for you. We may have to add
	 * more methods later as needed.
	 */

private ArrayList<Triple<Arithmetic>> sparse_matrix_list = new ArrayList<Triple<Arithmetic>>();	
private EnumArithmetic kind;	

/**Constructor for OrderedList object.
 * @param kind The Arithmetic type of Triples that this OrderedList contains.
 */
public OrderedList(EnumArithmetic kind){
	this.kind = kind;
}

/**Inserts Triple into OrderedList. Keeps it sorted by row number and column number.
 * @param t The Triple object to be inserted into the OrderedList.
 */
public void insertTriple(Triple<Arithmetic> t){
	if(sparse_matrix_list != null){
		if(sparse_matrix_list.isEmpty()){
			sparse_matrix_list.add(t);             // this type of add is a insert into the list
		}
		else{
			for(int i=0; i<sparse_matrix_list.size();i++){  
				if(sparse_matrix_list.get(i).isEqualLocation(t)){
					sparse_matrix_list.get(i).addTriple(t);
				}
				else if(sparse_matrix_list.get(i).compareTo(t) < 0){
					sparse_matrix_list.add(i, t);
					break;
				}
			}
			if(sparse_matrix_list.get(sparse_matrix_list.size() - 1).compareTo(t) > 0){
				sparse_matrix_list.add(t);
			}
		}
	} 
}


/** Removes Triple from ArrayList based on row number and column number.
 * @param row The row number of the element to be removed.
 * @param col The column number of the element to be removed.
 */
public void removeTriple(int row, int col){
	int index = this.getTripleIndex(row, col);
	if(index != -1){
	sparse_matrix_list.remove(index);
	}
}

/**
 * @param row Row number of the Triple object.
 * @param col Column number of the Triple object.
 * @return The index number in the OrderedList of the Triple located at 
 */
public int getTripleIndex(int row, int col){
	//use binary search or linear search to find it. Binary is better but I don't know how to do it yet.
	//return -1 if there is no Triple at location.
	int i=0;
	int j=sparse_matrix_list.size()-1;
	while(i<=j){
		int m=(i+j)/2;
		if(sparse_matrix_list.get(m).getRowNum() == row){
			
			/* while the rows remain the same check the column numbers */		
		
		
		}
		else if(sparse_matrix_list.get(m).getRowNum() > row){      //this.rows is greater than row
			j=m-1;
		}
		else if (sparse_matrix_list.get(m).getRowNum() < row){
			i=m+1;
		}
	}
	return -1;
}

/** Checks to see if the OrderedList is empty.
 * @return True if the OrderedList is empty. False if it contains at least one Triple.
 */
public boolean isEmpty(){
	return sparse_matrix_list.isEmpty();
}

/**Checks OrderedList for at least one instance of an Arithmetic value.
 * @param value The Arithmetic value to be searched for.
 * @return True if OrderedList contains at least one instance of this Arithmetic value.
 */
public boolean containsArithmeticValue(Arithmetic value){
	for(int i=0; i<sparse_matrix_list.size();i++){
		if(sparse_matrix_list.get(i).getValue() == value){
			return true;
		}
	}
	return false;
}

/**Checks OrderedList for a an Arithmetic value at a certain location by row number and column number.
 * @param row Row number for location.
 * @param col Column number for loation.
 * @return True if OrderedList has an Arithmetic value at given location.
 */
public boolean containsValueAtLocation(int row, int col){
	return (getTripleIndex(row,col) > 0);
}

/**Checks for a specific Triple.
 * @param t Triple to be searched for.
 * @return True if OrderedList contains Triple t.
 */
public boolean containsTriple(Triple<Arithmetic> t){
	int m = getTripleIndex(t.getRowNum(),t.getColNum());
	if(m > 0){
		return (sparse_matrix_list.get(m).equals(t));
	 }
	 return false;
}
/**Get the Arithmetic type that this OrderedList contains.
 * @return The EnumArithmetic kind of this OrderedList.
 */
public EnumArithmetic getKind(){
	return kind;
}

/**Returns a clone of this OrderedList.
 * @return Clone of this OrderedList.
 */
public OrderedList getOrderedList()throws CloneNotSupportedException{
	return (OrderedList)this.clone();
}

public OrderedList negate(){
	OrderedList neg = getOrderedList();	
	for(int i=0; i<sparse_matrix_list.size();i++){
		neg.sparse_matrix_list.get(i).negate();
	}
	return neg;
}
	
	public String toString(){
	StringBuilder sb = new StringBuilder();
	sb.append("[ ");
	for(int i=0; i < sparse_matrix_list.size(); i++ ){
		sb.append(sparse_matrix_list.get(i).getValue() + " ");
	}
	sb.append("]");
	return sb.toString();
}

}
