import java.io.*;
import java.util.*;
import static java.lang.System.out;

public class MixedFractions {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int numerator = in.nextInt();
			int denominator = in.nextInt();
			if (numerator == 0 && denominator == 0) {
				break;
			}
			out.printf("%d %d / %d%n", numerator/denominator, numerator%denominator, denominator);
		}
	}
	
	public static void main(String[] args) {
		new MixedFractions().go();
	}
}
