import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Loowater {
    
    public void go() {
        Kattio io = new Kattio(System.in);
        while (io.hasNext()) {
            int numHeads = io.nextInt();
            int numKnights = io.nextInt();
            if (numHeads == 0 && numKnights == 0) {
                break;
            }
            int[] heads = new int[numHeads];
            ArrayList<Integer> knights = new ArrayList<>(numKnights);
            for (int i = 0; i < numHeads; i++) {
                heads[i] = io.nextInt();
            }
            Arrays.sort(heads);
            for (int i = 0; i < numKnights; i++) {
                knights.add(io.nextInt());
            }
            Collections.sort(knights);
            if (heads.length > knights.size()) {
                io.println("Loowater is doomed!");
                continue;
            }
            int sum = 0;
            for (int i = heads.length-1; i >= 0; i--) {
                int index = Collections.binarySearch(knights, heads[i]);
                if (index < 0) {
                    index = ~index;
                }
                if (index == knights.size()) {
                    io.println("Loowater is doomed!");
                    sum = 0;
                    break;
                } else {
                    sum += knights.remove(index);
                }
            }
            if (sum != 0) {
                io.println(sum);
            }
        }
        
        io.flush();
        io.close();
    }
    
    public static void main(String[] args) {
        new Loowater().go();
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