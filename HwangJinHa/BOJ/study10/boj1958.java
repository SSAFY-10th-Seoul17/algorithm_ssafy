import java.io.*;
import java.util.*;

public class Main {
	static String a, b, c;
	static int[][][] lcs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;
//		StringBuilder sb = new StringBuilder();
		
		a = " " + br.readLine();
		b = " " + br.readLine();
		c = " " + br.readLine();
		
		int aSize = a.length();
		int bSize = b.length();
		int cSize = c.length();

		lcs = new int[aSize][bSize][cSize];

		for (int ai = 1; ai < aSize; ai++) {
			for (int bi = 1; bi < bSize; bi++) {
				for (int ci = 1; ci <cSize; ci++) {
					if (a.charAt(ai) == b.charAt(bi) && b.charAt(bi) == c.charAt(ci)) {
						lcs[ai][bi][ci] = lcs[ai-1][bi-1][ci-1] + 1;
					}
					else {
						lcs[ai][bi][ci] = Math.max(lcs[ai-1][bi][ci], Math.max(lcs[ai][bi-1][ci], lcs[ai][bi][ci-1]));
					}
				}
			}
		}
		System.out.println(lcs[aSize-1][bSize-1][cSize-1]);
	}
}
