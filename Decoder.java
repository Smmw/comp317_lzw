import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
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
		// Calculate the limit on dictionary entries
		// -2 as there is a reserved phrase incase the BitPacker wants it
		int limit = (int)Math.pow(2, maxBits)-2;
		// Initialise dictionary with all bytes
		ArrayList<Pair<Integer, Byte>> d = makeDictionary(limit);
		// The input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// A stack of sorts for the bytes
		LinkedList<Byte> bytes = new LinkedList<Byte>();
		try{
			// Read a value
			int value = Integer.parseInt(in.readLine());
			int lastValue = -1;
			Byte lastFirstByte = (byte)0;
			// While not done
			while (value >= 0){
				// Get data for value
				Pair<Integer, Byte> p;
				if (value < d.size()){
					p = d.get(value);
				} else {
					// Check if the dictionary is full
					if (d.size() >= limit){
						// This shouldn't have happened!
						throw new RuntimeException("The encoder is using a higher maxnumbits value!");
					}
					// This is the index we havn't added yet.
					// Add it
					d.add(new Pair<Integer, Byte>(lastValue, lastFirstByte));
					// Make sure you don't add it again
					lastValue = -1;
					// Go on as normal
					p = d.get(value);
				}
				do {
					bytes.addLast(p.getRight());
					p = p.getLeft() != null? d.get(p.getLeft()) : null;
				} while (p != null);
				// Set last first byte
				lastFirstByte = bytes.getLast();
				// Check if a new pair needs to be added to the dictionary
				if (lastValue >= 0 && d.size() < limit){
					d.add(new Pair<Integer, Byte>(lastValue, lastFirstByte));
				}
				// Output data for value
				while (bytes.size() > 0){
					System.out.write(bytes.removeLast());
				}
				// Read new value
				lastValue = value;
				value = Integer.parseInt(in.readLine());
				// Go to top of loop with new value
			}
		} catch (Exception e){
			// Flush standard out just incase there is data there
			System.out.flush();
			// Print unfun message
			System.err.println("A fatal error has occured.");
			// Drive it home
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
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
