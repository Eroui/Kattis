import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Doorman {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int threshold = in.nextInt();
		int numMen = 0, numWomen = 0;
		char[] line = in.next().toCharArray();
		int i = 0;
		for (; i < line.length; i++) {
			char c = line[i];
			if (c == 'M') {
				if (numMen-numWomen == threshold) {
					if (i < line.length-1 && line[i+1] == 'W') {
						line[i+1] = 'M';
						numWomen++;
					} else {
						break;
					}
				} else {
					numMen++;
				}
			} else {
				if (numWomen-numMen == threshold) {
					if (i < line.length-1 && line[i+1] == 'M') {
						line[i+1] = 'W';
						numMen++;
					} else {
						break;
					}
				} else {
					numWomen++;
				}
			}
		}
		out.println(i);
		
		in.close();
	}
	
	public static void main(String[] args) {
		new Doorman().go();
	}
}
