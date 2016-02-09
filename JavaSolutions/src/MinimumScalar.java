import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class MinimumScalar {
    
    Kattio io;
    
    public void go() {
        io = new Kattio(System.in);
        int zz = io.nextInt();
        for (int zzz = 0; zzz < zz; zzz++) {
            int size = io.nextInt();
            long[] one = new long[size];
            long[] two = new long[size];
            for (int i = 0; i < size; i++) {
                one[i] = io.nextInt();
            }
            for (int i = 0; i < size; i++) {
                two[i] = io.nextInt();
            }
            Arrays.sort(one);
            Arrays.sort(two);
            long total = 0;
            for (int i = 0; i < size; i++) {
                total += one[i] * two[size-i-1];
            }
            io.printf("Case #%d: %d%n", zzz+1, total);
        }
        
        io.flush();
        io.close();
    }
    
    public static void main(String[] args) {
        new MinimumScalar().go();
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
