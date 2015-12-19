import java.io.*;
import java.util.*;
import static java.lang.System.out;

public class SymmetricOrder {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int currSet = 0;
		while (in.hasNextInt()) {
			currSet++;
			int numNames = in.nextInt();
			if (numNames == 0) {
				break;
			}
			String[] names = new String[numNames];
			for (int i = 0; i < numNames; i++) {
				names[i] = in.next();
			}
			out.printf("SET %d%n", currSet);
			int i = 0;
			for (i = 0; i < numNames; i+=2) {
				out.println(names[i]);
			}
			if (numNames%2 == 1) {
				i -= 3;
			} else {
				i--;
			}
			for (; i >= 1; i-=2) {
				out.println(names[i]);
			}
		}
	}
	
	public static void main(String[] args) {
		new SymmetricOrder().go();
	}
}
 