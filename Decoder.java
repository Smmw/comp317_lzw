import java.util.ArrayList;
/**
 * Reads strings from standard in
 * Decompresses them with LZW using bytes as symbols
 * Outputs bytes representing the bytes of the file
 *
 * Severin Mahoney-Marsh 2014
 * 1181754
 */
public class Decoder{
	public static void main(String[] args){
		// Default settings
		int maxBits = 20;

		// Read args
		// -b Maximum bits per phrase
		if (args.length == 2){
			if (args[0].equals("-b")){
				try {
					maxBits = Integer.parseInt(args[1]);
				} catch (NumberFormatException e) {
					System.err.println("An integer must follow -b");
					return;
				}
			} else { 
				System.err.println("The only valid argument is -b");
				return;
			}
		} else if (args.length != 0){
			System.err.println("The only valid argument is -b maxnumbits");
		}

		// Undo LZW
		// Initialise dictionary with all bytes

		// Read a value
		// While not done
		// Output data for value
		// Read new value
		// Add start of new value's data to end of new dictionary entry for value
		// Go to top of loop with new value
	}

	/*
	 * I'm going to say it now, generics in generics looks distasteful
	 *
	 * This method builds an arraylist of the maximum size containing every
	 * possible first byte. They arraylist is used because it's a nice version of an array
	 * and arrays are lean. Just look at this:
	 * 	add (E element) O(1)
	 * 	get (E element) O(1)
	 * As long as you don't go resizing it this thing is gold.
	 */
	private static ArrayList<Pair<Integer, Byte>> makeDictionary(int maxSize){
		// The dictionary, at least all this strict typing helps java sleep at night
		ArrayList<Pair<Integer, Byte>> d = new ArrayList<Pair<Integer, Byte>>(maxSize);
		// The data
		for (int i = 0; i < 256; i++){
			d.add(new Pair<Integer, Byte>(null, new Byte((byte)i)));
		}
		// The result
		return d;
	}
	
	/**
	 * Kind of like a baby tuple
	 * Just be glad I'm not using object arrays like Ruby
	 *
	 * Stores a pair of things
	 * Is imutable
	 *
	 * Severin Mahoney-Marsh 2014
	 * 1181754
	 */
	private static class Pair<A, B>{
		A left;
		B right;

		public Pair(A left, B right){
			this.left = left;
			this.right = right;
		}

		/*
		 * Gets left
		 */
		public A getLeft(){
			return left;
		}

		/* 
		 * Gets right
		 */
		public B getRight(){
			return right;
		}
	}
}
