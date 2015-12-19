import java.io.*;
import java.util.*;
import java.util.regex.*;
import static java.lang.System.out;

public class ReverseRot {
	
	char[] ar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ_.".toCharArray();
	
	public void go() {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int rotate = in.nextInt();
			if (rotate == 0) {
				break;
			}
			char[] message = in.next().toCharArray();
			for (int i = 0; i < message.length; i++) {
				message[i] = ar[(indexOf(message[i])+rotate) % 28];
			}
			out.println(new StringBuilder(Arrays.toString(message).replaceAll("[\\[\\], ]", "")).reverse());
		}
	}
	
	public int indexOf(char c) {
		for (int i = 0; i < ar.length; i++) {
			if (ar[i] == c) {
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		new ReverseRot().go();
	}
}
