import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class NPuzzle {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		char[][] mat = new char[4][];
		for (int i = 0; i < 4; i++) {
			mat[i] = in.nextLine().trim().toCharArray();
		}
		int sum = 0;
		for (char c = 'A'; c <= 'O'; c++) {
			for (int i = 0; i < 4; i++) {
				for (int e = 0; e < 4; e++) {
					if (mat[i][e] == c) {
						sum += Math.abs((c-'A')/4-i) + Math.abs((c-'A')%4-e);
					}
				}
			}
		}
		out.println(sum);
		in.close();
	}
	
	public static void main(String[] args) {
		new NPuzzle().go();
	}
}
