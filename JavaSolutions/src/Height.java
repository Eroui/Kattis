import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Height {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		for (int zzz = 0; zzz < zz; zzz++) {
			int dataSet = in.nextInt();
			int[] students = new int[20];
			int sum = 0;
			for (int i = 0; i < 20; i++) {
				students[i] = in.nextInt();
				for (int j = 0; j < i; j++) {
					if (students[i] < students[j]) {
						insert(students[i], j, students);
						sum += i-j;
						break;
					}
				}
			}
			out.printf("%d %d%n", dataSet, sum);
		}
		
		in.close();
	}
	
	public void insert(int num, int j, int[] students) {
		for (int i = students.length-2; i >= j; i--) {
			students[i+1] = students[i];
		}
		students[j] = num;
	}
	
	public static void main(String[] args) {
		new Height().go();
	}
}
