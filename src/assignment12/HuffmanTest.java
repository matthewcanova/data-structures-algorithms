package assignment12;

import java.io.File;

/**
 * A Class for testing and exercising the HuffmanTree Class. Requires the
 * correct files be available in the project root.
 * 
 * @author Boqian Yao and Matthew Canova
 * 
 */
public class HuffmanTest {
	/**
	 * Helper method for compressing a file
	 * 
	 * @param infile file to be compressed
	 * @param outfile compressed file
	 */
	public static void compressFile(String infile, String outfile) {
		HuffmanTree t = new HuffmanTree();

		t.compressFile(new File(infile), new File(outfile));

		t.huffmanToDot("huffman_tree.dot");
	}

	/**
	 * Helper method for decompressing a file
	 * 
	 * @param infile file to be decompressed
	 * @param outfile decompressed file
	 */
	public static void decompressFile(String infile, String outfile) {
		HuffmanTree t = new HuffmanTree();

		t.decompressFile(new File(infile), new File(outfile));
	}

	public static void main(String[] args) {

		// The decompressed01 file should match the onlineFile01.
		compressFile("compressionTest1.txt", "compressed01.txt");
		//decompressFile("compressed01.txt", "decompressed01.txt");

		// The decompressed02 file should match the onlineFile02.
		compressFile("compressionTest2.txt", "compressed02.txt");
		//decompressFile("compressed02.txt", "decompressed02.txt");

		// The decompressed03 file should match the onlineFile03.
		compressFile("compressionTest3.txt", "compressed03.txt");
		//decompressFile("compressed03.txt", "decompressed03.txt");

		// The decompressed04 file should match the onlineFile04.
		compressFile("compressionTest4.txt", "compressed04.txt");
		//decompressFile("compressed04.txt", "decompressed04.txt");

		// The decompressed05 file should match the onlineFile05.
		compressFile("compressionTest5.txt", "compressed05.txt");
		//decompressFile("compressed05.txt", "decompressed05.txt");

	}
}
