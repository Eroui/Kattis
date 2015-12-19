import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Simon {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		for (int zzz = 0; zzz < zz; zzz++) {
			String next = in.nextLine();
			if (next.startsWith("simon says ")) {
				out.println(next.replaceFirst("simon says ", ""));
			} else {
				out.println("");
			}
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new Simon().go();
	}
}
