import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Beavergnaw {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int treeDiameter = in.nextInt();
			int maxVolume = in.nextInt();
			if (treeDiameter == 0 && maxVolume == 0) {
				break;
			}
			double smallDiameter;
			double top = treeDiameter;
			double bottom = 0;
			while (true) {
				smallDiameter = (top+bottom)/2;
//				out.println(smallDiameter);
				double calc = calcVolume(treeDiameter, smallDiameter);
				if (Math.abs(calc - maxVolume) < 0.0000001) {
					out.printf("%.9f%n", smallDiameter);
					break;
				} else if (calc > maxVolume) {
					bottom = smallDiameter;
				} else {
					top = smallDiameter;
				}
			}
//			out.println(calcVolume(treeDiameter, smallDiameter));
		}
		
		in.close();
	}
	
	public double calcVolume(double treeD, double smallD) {
		return Math.PI*(treeD*treeD*treeD/6 - smallD*smallD*smallD/6);
	}
	
	public static void main(String[] args) {
		new Beavergnaw().go();
	}
}
