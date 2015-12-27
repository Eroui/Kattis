import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class FourThought {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		char[] ops = {'*','/','+','-'};
		for (int zzz = 0; zzz < zz; zzz++) {
			int num = in.nextInt();
			boolean found = false;
			OUTER:
			for (int o1 = 0; o1 < 4; o1++) {
				for (int o2 = 0; o2 < 4; o2++) {
					for (int o3 = 0; o3 < 4; o3++) {
						boolean[] hasDone = new boolean[3];
						int res = 0;
						if (o3/2 < o2/2) {
							res = eval(4, 4, ops[o3]);
							hasDone[2] = true;
						}
						if (hasDone[2]) {
							res = eval(eval(4, 4, ops[o1]), res, ops[o2]);
						} else {
							if (o2/2 < o1/2) {
								if (o3/2 < o1/2) {
									res = eval(4, eval(eval(4, 4, ops[o2]), 4, ops[o3]), ops[o1]);
								} else {
									res = eval(eval(4, eval(4, 4, ops[o2]), ops[o1]), 4, ops[o3]);
								}
							} else {
								res = eval(eval(eval(4, 4, ops[o1]), 4, ops[o2]), 4, ops[o3]);
							}
						}
						if (res == num) {
							out.printf("4 %c 4 %c 4 %c 4 = %d%n", ops[o1], ops[o2], ops[o3], num);
							found = true;
							break OUTER;
						}
//						if (o1 == 2 && o2 == 0 && o3 == 0) {
//							out.printf("    4 %c 4 %c 4 %c 4 = %d%n", ops[o1], ops[o2], ops[o3], res);
//						}
					}
				}
			}
			if (!found) {
				out.println("no solution");
			}
		}
		
		in.close();
	}
	
	public int eval(int one, int two, char op) {
		switch(op) {
		case '*':
			return one*two;
		case '/':
			return one/two;
		case '+':
			return one+two;
		case '-':
			return one-two;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		new FourThought().go();
	}
}
