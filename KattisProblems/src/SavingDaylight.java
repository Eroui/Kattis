import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class SavingDaylight {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String month = in.next();
			int day = in.nextInt();
			int year = in.nextInt();
			String[] time1 = in.next().split(":");
			String[] time2 = in.next().split(":");
			int hours = Integer.parseInt(time2[0]) - Integer.parseInt(time1[0]);
			int minutes;
			if (Integer.parseInt(time2[1]) < Integer.parseInt(time1[1])) {
				hours--;
				minutes = Integer.parseInt(time2[1]) - Integer.parseInt(time1[1]) + 60;
			} else {
				minutes = Integer.parseInt(time2[1]) - Integer.parseInt(time1[1]);
			}
			out.printf("%s %d %d %d hours %d minutes%n", month, day, year, hours, minutes);
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new SavingDaylight().go();
	}
}
