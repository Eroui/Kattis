import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class JustAMinute {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		double average = 0;
		int minutesMeasured = 0;
		int numMeasurements = in.nextInt();
		for (int i = 0; i < numMeasurements; i++) {
			int expected = in.nextInt()*60;
			int actual = in.nextInt();
			average += (double)actual/expected*(expected/60);
			minutesMeasured += expected/60;
		}
		if (average <= minutesMeasured) {
			out.println("measurement error");
		} else {
			out.printf("%.9f", average/minutesMeasured);
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new JustAMinute().go();
	}
}
