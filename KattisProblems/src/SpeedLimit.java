import java.io.*;
import java.util.*;
import static java.lang.System.out;

public class SpeedLimit {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int numValues = in.nextInt();
			if (numValues == -1) {
				break;
			}
			int currTime = 0;
			int total = 0;
			for (int i = 0; i < numValues; i++) {
				int speed = in.nextInt();
				int time = in.nextInt();
				total += speed * (time-currTime);
				currTime = time;
			}
			out.printf("%d miles%n", total);
		}
	}
	
	public static void main(String[] args) {
		new SpeedLimit().go();
	}
}
