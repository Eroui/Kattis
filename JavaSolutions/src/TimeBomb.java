import java.io.*;
import java.util.*;
import java.util.regex.*;
import static java.lang.System.out;

public class TimeBomb {
	
	static char[][][] nums = {{{'*','*','*'},{'*',' ','*'},{'*',' ','*'},{'*',' ','*'},{'*','*','*'}}, {{' ',' ','*'},{' ',' ','*'},{' ',' ','*'},{' ',' ','*'},{' ',' ','*'}}, 
		{{'*','*','*'},{' ',' ','*'},{'*','*','*'},{'*',' ',' '},{'*','*','*'}}, {{'*','*','*'},{' ',' ','*'},{'*','*','*'},{' ',' ','*'},{'*','*','*'}},
		{{'*',' ','*'},{'*',' ','*'},{'*','*','*'},{' ',' ','*'},{' ',' ','*'}}, {{'*','*','*'},{'*',' ',' '},{'*','*','*'},{' ',' ','*'},{'*','*','*'}},
		{{'*','*','*'},{'*',' ',' '},{'*','*','*'},{'*',' ','*'},{'*','*','*'}}, {{'*','*','*'},{' ',' ','*'},{' ',' ','*'},{' ',' ','*'},{' ',' ','*'}},
		{{'*','*','*'},{'*',' ','*'},{'*','*','*'},{'*',' ','*'},{'*','*','*'}}, {{'*','*','*'},{'*',' ','*'},{'*','*','*'},{' ',' ','*'},{'*','*','*'}}};
	
	public void go() {
		Scanner in = new Scanner(System.in);
		char[][] mat = new char[5][];
		for (int i = 0; i < 5; i++) {
			mat[i] = in.nextLine().toCharArray();
//			out.println(mat[i]);
		}
		int time = 0;
		for (int i = 0; i < mat[0].length; i+=4) {
			int num = -1;
			for (int n = 0; n < nums.length; n++) {
				boolean found = true;
				for (int r = 0; r < 5 && found; r++) {
					for (int c = 0; c < 3; c++) {
						if (nums[n][r][c] != mat[r][c+i]) {
//							out.printf("%c!=%c for n=%d at (%d,%d)%n", nums[n][r][c], mat[r][c+i], n, r, c+i);
							found = false;
							break;
						}
					}
				}
				if (found) {
					num = n;
					break;
				}
			}
			if (num != -1) {
//				out.printf("adding %d to time%n", num);
				time *= 10;
				time += num;
			} else {
				time = 1;
				break;
			}
		}
//		out.println(time);
		if (time%6 == 0) {
			out.println("BEER!!");
		} else {
			out.println("BOOM!!");
		}
	}
	
	public void makeNums() {
		Scanner in = new Scanner("***\n* *\n***\n  *\n***");
		char[][] mat = new char[5][3];
		for (int r = 0; r < 5; r++) {
			mat[r] = in.nextLine().toCharArray();
		}
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (int r = 0; r < 5; r++) {
			sb.append("{");
			for (int c = 0; c < 3; c++) {
				sb.append("'").append(mat[r][c]).append("'");
				if (c != 2) {
					sb.append(",");
				}
			}
			sb.append("}");
			if (r != 4) {
				sb.append(",");
			}
		}
		sb.append("}");
		out.println(sb);
	}
	
	public static void main(String[] args) {
		new TimeBomb().go();
	}
}
