import java.io.*;
import java.util.*;
import static java.lang.System.out;

public class Spavanac {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int hours = in.nextInt();
		int minutes = in.nextInt();
		minutes -= 45;
		if (minutes < 0) {
			hours -= 1;
			if (hours < 0) {
				hours = 23;
			}
			minutes += 60;
		}
		out.printf("%d %d", hours, minutes);
	}
	
	public static void main(String[] args) {
		new Spavanac().go();
	}
}
