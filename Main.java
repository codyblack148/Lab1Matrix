
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
		
		//Test the random Triple constructor.
		Triple<Arithmetic> 
		testRand1 = new Triple<Arithmetic>(true,EnumArithmetic.INTEGER,20,20),
		testRand2 = new Triple<Arithmetic>(true,EnumArithmetic.PRIME,20,20),
		testRand3 = new Triple<Arithmetic>(true,EnumArithmetic.RATIONAL,20,20),
		testRand4 = new Triple<Arithmetic>(true,EnumArithmetic.REAL,20,20);
		System.out.println(testRand1);
		System.out.println(testRand2);
		System.out.println(testRand3);
		System.out.println(testRand4);
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
		
		
		//Test the random SparseMatrix constructor.
		SparseMatrix sMatrix1 = new SparseMatrix(true,EnumArithmetic.INTEGER);
		System.out.println(sMatrix1.getSparseMatrixOrderedList());
		
}
		/*
	 * Cody Black Lookback
	 * I thought this project overall went well and I learned a lot while going through it. It was really
	 * the first time in a long time in a coding class where everything is not just laid out for you
	 * in a certain way, and you have to think for yourself and collaborate across different classes to
	 * achieve a goal. I learned how to write a compareTo method for the first time, and I was quite proud of
	 * accomplishing that. It was also my first time writing a class where some of the attributes of that class
	 * are objects of other classes that have been written. This lab was difficult, but the learning process
	 * throughout it was excellent.
	 * 
	 * Working with Adam Crawford was smooth and easy. We are well acquainted so it was easy to work together
	 * on this project. We utilized an online version control system called GitHub in order to collaborate on
	 * our work. This made the process efficient and simple. In fact, I would suggest that everyone learn how 
	 * to use GitHub and work in that way. I would work with Adam again.
	 */
	
	/* Adam Crawford LookBack
	This Lab was interesting because of the comlexity of the classes needed to make a sparse matrix work in coding. 
	I havent used a lot of get commands in most of my CS classes until this class. This lab had me using tons of
	get commands just to get a simple value. It is always enjoyable being able to think like a computer to make the
	next line of code predictable knowing that the next attribute call is only one line away. I have never used any 
	clone methods before so it was cool to see how they work and how to write them.
	
	Working with Cody has been a challenge. We had conflicting designs and conflicting ideas. Cody is able to write 
	faster than I can, So Cody did most of the coding. When the methods became complex I would take the time to write 
	and debug them while coding write the methods that would be used the most. We used GitHub to work on the code.
	Cody would work on the code each night, because of the difference in designs I had cody write the names of the 
	methods and what He wanted them to do, so I could come back later and either fix the code that needed debugging 
	or write the code needed for the method names he had left for me. 
	All in All I would work with Cody again.
	*/

}
