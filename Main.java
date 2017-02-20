
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
		
		//Test the insert method and check to make sure the OrderedList is sorted by using the toString.
		AInteger
		test5 = new AInteger("5"),
		test6 = new AInteger("6"),
		test7 = new AInteger("7"),
		test8 = new AInteger("8"),
		test9 = new AInteger("9"),
		test10 = new AInteger("10"),
		test11 = new AInteger("11"),
		test12 = new AInteger("12");
		
		Triple<Arithmetic> 
		t5 = new Triple<Arithmetic>(test5,0,0,EnumArithmetic.INTEGER),
		t6 = new Triple<Arithmetic>(test6,0,1,EnumArithmetic.INTEGER),
		t7 = new Triple<Arithmetic>(test7,0,2,EnumArithmetic.INTEGER),
		t8 = new Triple<Arithmetic>(test8,0,3,EnumArithmetic.INTEGER),
		t9 = new Triple<Arithmetic>(test9,1,0,EnumArithmetic.INTEGER),
		t10 = new Triple<Arithmetic>(test10,1,1,EnumArithmetic.INTEGER),
		t11 = new Triple<Arithmetic>(test11,1,2,EnumArithmetic.INTEGER),
		t12 = new Triple<Arithmetic>(test12,1,3,EnumArithmetic.INTEGER);
		OrderedList list = new OrderedList(EnumArithmetic.INTEGER);
		list.insertTriple(t12);
		System.out.println(list);
		list.insertTriple(t10);
		System.out.println(list);
		list.insertTriple(t5);
		System.out.println(list);
		list.insertTriple(t7);
		System.out.println(list);
		list.insertTriple(t6);
		System.out.println(list);
		list.insertTriple(t9);
		System.out.println(list);
		list.insertTriple(t8);
		System.out.println(list);
		list.insertTriple(t11);
		
		System.out.println(list);
		
		//Desired output: [ 5 6 7 8 9 10 11 12 ]
		//Produces desired results.
}
	
}
