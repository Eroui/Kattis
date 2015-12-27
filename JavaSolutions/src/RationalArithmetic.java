import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class RationalArithmetic {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		for (int zzz = 0; zzz < zz; zzz++) {
			Fraction one = new Fraction(in.nextLong(), in.nextLong());
			char op = in.next().charAt(0);
			Fraction two = new Fraction(in.nextLong(), in.nextLong());
			switch (op) {
			case '+':
				one.add(two);
				break;
			case '-':
				one.subtract(two);
				break;
			case '*':
				one.multiply(two);
				break;
			case '/':
				one.divide(two);
				break;
			}
			out.println(one);
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new RationalArithmetic().go();
	}
	
	private class Fraction {
		long numerator, denominator;
		
		public Fraction(long n, long d) {
			numerator = n;
			denominator = d;
			reduce();
			checkSign();
		}
		
		public void add(Fraction other) {
			long lcm = lcm(Math.abs(other.denominator), Math.abs(denominator));
			numerator *= lcm/denominator;
			numerator += other.numerator*(lcm/other.denominator);
			denominator = lcm;
			reduce();
			checkSign();
		}
		
		public void subtract(Fraction other) {
			long lcm = lcm(Math.abs(other.denominator), Math.abs(denominator));
			numerator *= lcm/denominator;
			numerator -= other.numerator*(lcm/other.denominator);
			denominator = lcm;
			reduce();
			checkSign();
		}
		
		public void multiply(Fraction other) {
			numerator *= other.numerator;
			denominator *= other.denominator;
			reduce();
			checkSign();
		}
		
		public void divide(Fraction other) {
			numerator *= other.denominator;
			denominator *= other.numerator;
			reduce();
			checkSign();
		}
		
		public String toString() {
			return String.format("%d / %d", numerator, denominator);
		}
		
		private void reduce() {
			long gcd = gcd(Math.abs(numerator), Math.abs(denominator));
			numerator /= gcd;
			denominator /= gcd;
		}
		
		private void checkSign() {
			if (denominator < 0) {
				numerator *= -1;
				denominator *= -1;
			}
		}
		
		private long gcd(long one, long two) {
			if (two == 0) {
				return one;
			}
			return gcd(two, one % two);
		}
		
		private long lcm(long one, long two) {
			return one*two/gcd(one,two);
		}
	}
}
