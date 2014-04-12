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
		// Read args
		// -b Maximum bits per phrase

		// Do LZW
		// Initialise tree with all possible bytes
		// While not done
		// Follow bytes untill they diverge from the trie
		// Output number from the last node on the trie
		// Add the mis-matching byte to the node on the trie
		// Go to top of loop with mis-matching byte
	}
}
