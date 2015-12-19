import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Classy {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		for (int zzz = 0; zzz < zz; zzz++) {
			int numPeople = in.nextInt();
			in.nextLine();
			Person[] people = new Person[numPeople];
			for (int i = 0; i < numPeople; i++) {
				people[i] = new Person(in.nextLine());
			}
			Arrays.sort(people);
			for (Person p : people) {
				out.println(p);
			}
			out.println("==============================");
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new Classy().go();
	}
	
	public class Person implements Comparable<Person> {
		String name;
		String rating;
		
		public Person(String line) {
			String[] split = line.split(":");
			name = split[0];
			rating = new StringBuilder(split[1].replaceAll("class", "").trim().replaceAll("upper", "0").replaceAll("middle", "1").replaceAll("lower", "2").replaceAll("-", "")).reverse().append("11111111111").toString();
		}
		
		public int compareTo(Person other) {
			if (this.personCompareTo(other) == 0) {
				return name.compareTo(other.name);
			}
			return this.personCompareTo(other);
		}
		
		private int personCompareTo(Person other) {
			int i = 0;
			for (; i < rating.length() && i < other.rating.length(); i++) {
				if (rating.charAt(i) != other.rating.charAt(i)) {
					return rating.charAt(i) - other.rating.charAt(i);
				}
			}
			return 0;
		}
		
		public String toString() {
			return name;
		}
	}
}
