import java.io.*;
import java.util.*;
import static java.lang.System.out;

public class DiceGame {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int[] emma, gunnar;
		int a1, b1, a2, b2, i;
		a1 = in.nextInt();
		b1 = in.nextInt();
		a2 = in.nextInt();
		b2 = in.nextInt();
		gunnar = new int[(b1-a1+1)*(b2-a2+1)];
		i = 0;
		for (int one = a1; one <= b1; one++) {
			for (int two = a2; two <= b2; two++) {
				gunnar[i++] = one+two;
			}
		}
		a1 = in.nextInt();
		b1 = in.nextInt();
		a2 = in.nextInt();
		b2 = in.nextInt();
		emma = new int[(b1-a1+1)*(b2-a2+1)];
		i = 0;
		for (int one = a1; one <= b1; one++) {
			for (int two = a2; two <= b2; two++) {
				emma[i++] = one+two;
			}
		}
		int emmaWin = 0;
		int gunnarWin = 0;
		for (int e = 0; e < emma.length; e++) {
			for (int g = 0; g < gunnar.length; g++) {
				if (emma[e] > gunnar[g]) {
					emmaWin++;
				} else if (emma[e] < gunnar[g]) {
					gunnarWin++;
				}
			}
		}
		if (emmaWin > gunnarWin) {
			out.println("Emma");
		} else if (emmaWin < gunnarWin) {
			out.println("Gunnar");
		} else {
			out.println("Tie");
		}
	}
	
	public static void main(String[] args) {
		new DiceGame().go();
	}
}
