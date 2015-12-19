import java.io.*;
import java.util.*;
import java.util.regex.*;
import static java.lang.System.out;

public class BusySchedule {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int numTimes = in.nextInt();
			in.nextLine();
			if (numTimes == 0) {
				break;
			}
			int[] times = new int[numTimes];
			for (int i = 0; i < numTimes; i++) {
				String[] t = in.nextLine().split("[: ]");
				int time = Integer.parseInt(t[0])%12*60 + Integer.parseInt(t[1]);
				if (t[2].equals("p.m.")) {
					time += 60*12;
				}
				times[i] = time;
			}
			Arrays.sort(times);
			for (int t : times) {
				if (t >= 60*12) {
					t -= 60*12;
					out.printf("%d:%02d p.m.%n", (t/60+11)%12+1, t%60);
				} else {
					out.printf("%d:%02d a.m.%n", (t/60+11)%12+1, t%60);
				}
			}
			out.println();
		}
	}
	
	public static void main(String[] args) {
		new BusySchedule().go();
	}
}
