import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Han {
    
    Kattio io;
    
    public void go() {
        io = new Kattio(System.in);
        int numCommands = io.nextInt();
        int i = 0;
        int dir = 1;
        int n = 0;
        int[] letters = new int[26];
        for (int z = 0; z < numCommands; z++) {
            String command = io.next();
            int num = io.nextInt();
            switch (command) {
            case "UPIT":
                int let = io.next().charAt(0)-'a';
                if (num-n >= 26) {
	                for (int l = 0; l < 26; l++) {
	                	letters[l] += (num-n)/26;
	                }
                }
                n += (num-n)/26*26;
                for (; n < num; n++) {
                    letters[i]++;
                    i += dir+26;
                    i %= 26;
                }
                io.println(letters[let]);
                break;
            case "SMJER":
            	if (num-n >= 26) {
	                for (int l = 0; l < 26; l++) {
	                	letters[l] += (num-n)/26;
	                }
                }
                n += (num-n)/26*26;
                for (; n < num; n++) {
                    letters[i]++;
                    i += dir+26;
                    i %= 26;
                }
                dir *= -1;
                i += dir*2+26;
                i %= 26;
                break;
            }
        }
        
        io.flush();
        io.close();
    }
    
    public static void main(String[] args) {
        new Han().go();
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
