/**
 * Bit Unpacker
 *
 * Take an input stream of bytes (binary) and decompress it by extracting
 * integers and outputting them in ASCII on a line.
 *
 * Dan Collins 2014
 * 1183446
 */

import java.io.IOException;

class Unpacker {
    /**
     * Calculate the number of bits needed to represent a number.
     * @param The value to test
     * @returns The minimum number of bits needed to represent the value
     *
     * @link http://stackoverflow.com/a/680040
     */
    public static int log2(int v) {
	return Integer.SIZE - Integer.numberOfLeadingZeros(v);
    }

    public static void main(String[] args) {
	int maxPhrase = 255;
	int maxBits;

	try {
	    maxBits = Integer.parseInt(args[0]);
	} catch (Exception e) {
	    e.printStackTrace();
	    return;
	}

	// Input data
	int inByte = 0;

	// Intermediate step
	int inBytePiece = 0;
	int phraseBits = 0;
	int inBits = 0;

	// Output data
	int phraseNumber = 0;

	try {
	    do {
		// If we're at the start of a new phrase, prepare to read the
		// bits
		if (phraseBits == 0) {
		    phraseBits = log2(maxPhrase);
		    phraseBits = phraseBits > maxBits ? maxBits : phraseBits;
		}

		// If we have bits from the last in byte, OR them into the
		// phrase
		if (inBits > 0) {
		    if (phraseBits < inBits) {
			// Get the bits to save, and prepare inByte for the next
			// loop.
			inBytePiece = inByte >> (8 - phraseBits);
			inByte &= (int)(Math.pow(2, 8 - phraseBits) - 1);
			// Load the bits into the output phrase
			phraseNumber <<= phraseBits;
			phraseNumber |= inBytePiece;
			// Set up for the next read
			inBits -= phraseBits;
			phraseBits = 0;
		    } else {
			// Load the read bits into the output phrase
			phraseNumber <<= inBits;
			phraseNumber |= inByte;
			// Set up for the next read
			phraseBits -= inBits;
			inBits = 0;
		    }
		}

		if (phraseBits == 0) {
		    // Output the phrase
		    System.out.println(phraseNumber);
		    phraseNumber = 0;
		    maxPhrase += 1;
		}

		if (inBits == 0) {
		    // Read the next byte
		    inByte = System.in.read();
		    inBits = 8;
		}
	    } while (inByte != -1);
	} catch (IOException e) {
	    System.err.println("Failed to read from input stream!");
	    e.printStackTrace();
	    return;
	}
    }
}
