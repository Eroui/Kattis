import java.io.*;
import java.util.*;
import java.util.regex.*;
import static java.lang.System.out;

public class Billiard {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int width = in.nextInt();
			int height = in.nextInt();
			int s = in.nextInt();
			int numVertBounces = in.nextInt();
			int numHorizBounces = in.nextInt();
			if (width == 0 && height == 0 && s == 0 && numVertBounces == 0 && numHorizBounces == 0) {
				break;
			}
			double angle = Math.toDegrees(Math.atan2(height*numHorizBounces, width*numVertBounces));
			double velocity = Math.sqrt(Math.pow(height*numHorizBounces, 2) + Math.pow(width*numVertBounces, 2))/s;
			out.printf("%.2f %.2f%n", angle, velocity);
		}
	}
	
	public static void main(String[] args) {
		new Billiard().go();
	}
}
