import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class MusicYourWay {
	
	Kattio io;
	
	public void go() {
		io = new Kattio(System.in);
		String[] categories = io.nextLine().trim().split(" ");
		int numSongs = io.nextInt();
		Song[] songs = new Song[numSongs];
		for (int i = 0; i < numSongs; i++) {
			String[] info = new String[categories.length];
			for (int e = 0; e < info.length; e++) {
				info[e] = io.next();
			}
			songs[i] = new Song(info);
		}
		int numSorts = io.nextInt();
		for (int i = 0; i < numSorts; i++) {
			String cat = io.next();
			int sortIndex = 0;
			for (int e = 0; e < categories.length; e++) {
				if (categories[e].equals(cat)) {
					sortIndex = e;
					break;
				}
			}
			Arrays.sort(songs, new SongComparator(sortIndex));
			if (i != 0) {
				io.println();
			}
			io.println(Arrays.toString(categories).replaceAll("[\\[\\],]", ""));
			io.println(Arrays.toString(songs).replaceAll("[\\[\\]]", "").replaceAll(", ", "\n"));
		}
		
		io.flush();
		io.close();
	}
	
	public static void main(String[] args) {
		new MusicYourWay().go();
	}
	
	private class Song {
		
		String[] info;
		
		public Song(String[] i) {
			info = i;
		}
		
		public String toString() {
			return Arrays.toString(info).replaceAll("[\\[\\],]", "");
		}
	}
	
	private class SongComparator implements Comparator<Song> {
		
		int index;
		
		public SongComparator(int i) {
			index = i;
		}
		
		public int compare(Song one, Song two) {
			return one.info[index].compareTo(two.info[index]);
		}
	}
	
	private class Kattio extends PrintWriter {

	    private BufferedReader r;
	    private String line;
	    private StringTokenizer st;
	    private String token;
		
	    public Kattio(InputStream i) {
			super(new BufferedOutputStream(System.out));
			r = new BufferedReader(new InputStreamReader(i));
	    }
	    public Kattio(InputStream i, OutputStream o) {
			super(new BufferedOutputStream(o));
			r = new BufferedReader(new InputStreamReader(i));
	    }

	    public boolean hasNext() {
	    	return peekToken() != null;
	    }

	    public int nextInt() {
	    	return Integer.parseInt(nextToken());
	    }

	    public double nextDouble() { 
	    	return Double.parseDouble(nextToken());
	    }

	    public long nextLong() {
	    	return Long.parseLong(nextToken());
	    }

	    public String next() {
	    	return nextToken();
	    }
	    
	    public String nextLine() {
	    	token = null;
	    	st = null;
	    	try {
	    		return r.readLine();
	    	} catch (IOException e) {
	    		return null;
	    	}
	    }

	    private String peekToken() {
			if (token == null) 
			    try {
					while (st == null || !st.hasMoreTokens()) {
					    line = r.readLine();
					    if (line == null) return null;
					    st = new StringTokenizer(line);
					}
					token = st.nextToken();
			    } catch (IOException e) { }
			return token;
	    }

	    private String nextToken() {
			String ans = peekToken();
			token = null;
			return ans;
	    }
	}
}
