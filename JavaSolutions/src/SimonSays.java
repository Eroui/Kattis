import java.io.*;
import java.util.*;
import static java.lang.System.out;

public class SimonSays {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		for (int zzz = 0; zzz < zz; zzz++) {
			String line = in.nextLine();
			if (line.startsWith("Simon says"))
				out.println(line.replaceAll("Simon says", ""));
		}
	}
	
	public static void main(String[] args) {
		new SimonSays().go();
	}
}
