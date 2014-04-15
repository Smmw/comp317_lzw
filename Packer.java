/**
 * Bit Packer
 *
 * Take an input stream of integers (ASCII) and compress them by packing each
 * value into as few bits as possible.  Data will be stored MSB first.
 *
 * Dan Collins 2014
 * 1183446
 */

import java.io.*;

class Packer {
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

	// Input data
	String inLine;
	int phraseNumber;
	BufferedReader reader;

	// Output byte
	byte outByte = 0; // The byte to be written to the output stream
	int outBits = 8; // The number of bits free in the byte

	// Intermediate step
	int phraseNumberPiece; // Fraction of the phrase number for copying
	int phraseBits; // The number of bits our phrase needs

	// Input will be read from stdin
	reader = new BufferedReader(new InputStreamReader(System.in));

	try {
	    while ((inLine = reader.readLine()) != null) {
		// Read the phrase number
		try {
		    phraseNumber = Integer.parseInt(inLine);
		} catch (Exception e) {
		    System.err.printf("Invalid integer (%s)\n", inLine);
		    continue;
		}
		
		// Prepare to pack the bits
		phraseBits = log2(maxPhrase);
		
		// Output as many bytes as we need to
		while (phraseBits > outBits) {
		    outByte <<= outBits;
		    // Shift down the bits we want to send, and mask them
		    phraseNumberPiece = phraseNumber >> (phraseBits - outBits);
		    phraseNumberPiece &= (int)(Math.pow(2, outBits) - 1);
		    phraseBits -= outBits;
		    // OR in the bits
		    outByte |= phraseNumberPiece;
		    // Flush the byte
		    System.out.printf("%x\n", outByte);
		    outByte = 0;
		    outBits = 8;
		}

		// Copy the left over data if we have any
		if (phraseBits > 0) {
		    // Mask and OR in the data
		    phraseNumberPiece = (phraseNumber &
					 (int)(Math.pow(2, phraseBits) - 1));
		    outByte |= phraseNumberPiece;
		    // Update the number of free bits
		    outBits -= phraseBits;
		}

		// Flush the byte if we need to
		if (outBits == 0) {
		    System.out.printf("%x\n", outByte);
		    outByte = 0;
		    outBits = 8;
		}

		// There's now one more phrase in the trie
		maxPhrase += 1;
	    }

	    // If we have data left, right pad with zero
	    if (outBits < 8) {
		outByte <<= outBits;
		System.out.printf("%x\n", outByte);
	    }
	} catch (Exception e) {
	    System.err.println("Failed to read from input stream!");
	    return;
	}
    }

    static String getBits(int val, int len) {
	String ret = "";
	while(len-- > 0) {
	    if ((val & 1) == 1)
		ret = "1" + ret;
	    else
		ret = "0" + ret;
	    val >>>= 1;
	}

	return ret;
    }
}