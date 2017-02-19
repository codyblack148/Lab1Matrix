
/**Main class to test everything for Triple, OrderedList and SparseMatrix.
 * @author codyblack
 *
 */
public class Main {

	
	public static void main(String[] args) {
		
		/*
		 * The following code will test some of the methods of the Triple class to ensure functionality.
		 */
		
		// Test the Triple compareTo method.
		AInteger 
		test1 = new AInteger("1"),
		test2 = new AInteger("2");
		Triple<Arithmetic> 
		t1 = new Triple<Arithmetic>(test1,0,0,EnumArithmetic.INTEGER),
		t2 = new Triple<Arithmetic>(test2,0,1,EnumArithmetic.INTEGER),
		t3 = new Triple<Arithmetic>(test1,5,5,EnumArithmetic.INTEGER),
		t4 = new Triple<Arithmetic>(test1,5,5,EnumArithmetic.INTEGER);
		System.out.println(t1.compareTo(t2));
		System.out.println(t2.compareTo(t1));
		System.out.println(t3.compareTo(t4));
		System.out.println(t4.compareTo(t3));   // produces desired results. Method works.
		
		// Test the Triple toString method.
		System.out.println(t1); // produces desired results. Method works.
		
		//Test the Triple equals method.
		System.out.println(t3.equals(t4));
		System.out.println(t1.equals(t2));  // produces desired results. Method works.
		
		//Test the Triple add and subtract methods.(This will inadvertently test the changeTripleValue method as well)
		t1.addTriple(t2);
		System.out.println(t1); 
		t1.subtractTriple(t3);
		System.out.println(t1); // produces desired results. All 3 methods work.
		
		/*
		 * The following code will test some of the methods of the OrderedList class to ensure functionality.
		 */
		
		//
}
	
}
