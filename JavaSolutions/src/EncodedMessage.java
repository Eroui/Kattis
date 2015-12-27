import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class EncodedMessage {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		for (int zzz = 0; zzz < zz; zzz++) {
			String input = in.nextLine();
			int size = (int)Math.round(Math.sqrt(input.length()));
			StringBuilder sb = new StringBuilder();
			for (int c = size-1; c >= 0; c--) {
				for (int r = 0; r < size; r++) {
					sb.append(input.charAt(r*size+c));
				}
			}
			out.println(sb);
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new EncodedMessage().go();
	}
}
