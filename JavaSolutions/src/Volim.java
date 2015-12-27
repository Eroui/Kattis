import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Volim {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int index = in.nextInt()-1;
		int numQuestions = in.nextInt();
		int currTime = 210;
		for (int zz = 0; zz < numQuestions; zz++) {
			int time = in.nextInt();
			char answer = in.next().charAt(0);
			currTime -= time;
			if (currTime <= 0) {
				out.println(index+1);
				break;
			}
			switch (answer) {
			case 'T':
				index++;
				index %= 8;
			}
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new Volim().go();
	}
}
