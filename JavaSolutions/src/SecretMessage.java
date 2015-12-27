import java.io.*;
import java.util.*;
import java.util.regex.*;
import static java.lang.System.out;

public class SecretMessage {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		for (int zzz = 0; zzz < zz; zzz++) {
			String line = in.nextLine();
			int size = (int)Math.ceil(Math.sqrt(line.length()));
			char[][] mat = new char[size][size];
			for (int i = 0; i < mat.length; i++) {
				Arrays.fill(mat[i], '*');
			}
			for (int i = 0; i < line.length(); i++) {
				mat[i/size][i%size] = line.charAt(i);
			}
			StringBuilder sb = new StringBuilder();
			for (int c = 0; c < size; c++) {
				for (int r = size-1; r >= 0; r--) {
					if (mat[r][c] != '*') {
						sb.append(mat[r][c]);
					}
				}
			}
			out.println(sb);
		}
	}
	
	public static void main(String[] args) {
		new SecretMessage().go();
	}
}
