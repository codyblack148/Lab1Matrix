import java.util.ArrayList;


/**OrderedList represents an ArrayList of Triples in sorted order.
 * @author codyblack
 *
 */
public class OrderedList implements Cloneable {

private ArrayList<Triple> sparse_matrix_list = new ArrayList<Triple>();	
private EnumArithmetic kind;	

/**
 * @param kind The Arithmetic type of Triples that this OrderedList contains.
 */
public OrderedList(EnumArithmetic kind){
	this.kind = kind;
}

/**Inserts Triple into OrderedList. Keeps it sorted.
 * @param t The Triple object to be inserted into the OrderedList.
 */
public void insertTriple(Triple<Arithmetic> t){
	if(sparse_matrix_list != null){
		if(sparse_matrix_list.isEmpty()){
			sparse_matrix_list.add(t);
		}
		else{
			for(int i=0; i<sparse_matrix_list.size();i++){
				if(sparse_matrix_list.get(i).isEqualLocation(t)){
					sparse_matrix_list.get(i).addTriple(t);
				}
				else if(sparse_matrix_list.get(0).compareTo(t) < 0){
					sparse_matrix_list.add(0, t);
					break;
				}
				else if(sparse_matrix_list.get(i).compareTo(t) < 0){
					//t needs to be inserted at preceding index.
					sparse_matrix_list.add(i-1, t);
					break;
				}
				
			}
			if(sparse_matrix_list.get(sparse_matrix_list.size() - 1).compareTo(t) > 0){
				sparse_matrix_list.add(t);
			}
		}
	}
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
