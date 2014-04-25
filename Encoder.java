import java.io.IOException;

/**
 * Reads bytes from standard in
 * Compresses them with LZW using bytes as symbols
 * Outputs strings representing the value that would be bitpacked
 *
 * Severin Mahoney-Marsh 2014
 * 1181754
 */
public class Encoder{
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
			return;
		}

		// Do LZW
		// Initialise tree with all possible bytes, the limit is subtracted by two
		// one bit because you have too and one bit just incase the packer wants a flag
		SuffixTrie<Byte> trie = generateTrie((int)Math.pow(2, maxBits)-2);
		// While not done
		try {
			Byte b = (byte)System.in.read();
			boolean cleanBreak = false;
			double bytesIn = 0;
			double bytesOut = 0;
			int check = 1000;
			while (System.in.available() > 0){
				// Check if compression is adiquate
				if (check <= 0){
					// Inadiquate!
					if (bytesIn - bytesOut > 0){
						trie = generateTrie((int)Math.pow(2, maxBits)-2);
					}
					bytesIn = 0;
					bytesOut = 0;
					check = 1000;
				}
				// Decrement check counter
				if (trie.isFull()){
					check -= 1;
				}
				// Follow bytes untill they diverge from the trie
				while (!trie.step(b)){
					int temp = System.in.read();
					if (temp == -1){
						cleanBreak = true;
						break;
					}
					b = (byte)temp;
					if (trie.isFull()) bytesIn += 1;
				}
				// Output number from the last node on the trie
				System.out.printf("%d%n", trie.getIndex());
				if (trie.isFull()) bytesOut += maxBits / 8.0;
				// Add the mis-matching byte to the node on the trie
				trie.add(b);
				// Go to top of loop with mis-matching byte
				// TODO: Throw away trie when compression is bad
				trie.reset();
			}
			// Handle an edge case on end of stream
			if (!cleanBreak){
				trie.reset();
				trie.step(b);
				System.out.printf("%d%n", trie.getIndex());
			}
		} catch (IOException e){
			System.err.println("Alas I have been slain by an IO error.");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		System.out.flush();
	}

	/*
	 * Generates a trie with all possible bytes
	 */
	private static SuffixTrie<Byte> generateTrie(int limit){
		SuffixTrie<Byte> trie = new SuffixTrie<Byte>(limit);
		for (int i = 0; i < 256; i++){
			trie.add((byte)i);
		}
		return trie;
	}
}
