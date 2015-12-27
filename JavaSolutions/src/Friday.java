import java.io.*;
import java.util.*;
import java.util.regex.*;
import static java.lang.System.out;

public class Friday {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		for (int zzz = 0; zzz < zz; zzz++) {
			int numDays = in.nextInt();
			int numMonths = in.nextInt();
			int offset = 0;
			int numFridays = 0;
			for (int i = 0; i < numMonths; i++) {
				int month = in.nextInt();
				if (offset == 0 && month >= 13) {
					numFridays++;
				}
				offset += month;
				offset %= 7;
			}
			out.println(numFridays);
		}
	}
	
	public static void main(String[] args) {
		new Friday().go();
	}
}
