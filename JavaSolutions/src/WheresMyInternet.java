import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class WheresMyInternet {

    Kattio io;

    public void go() {
        io = new Kattio(System.in);
        int numHouses = io.nextInt();
        int numConnections = io.nextInt();
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        boolean[] connected = new boolean[numHouses];
        for (int i = 0; i < numConnections; i++) {
            int one = io.nextInt()-1;
            int two = io.nextInt()-1;
            if (map.get(one) == null) {
                map.put(one, new HashSet<Integer>());
            }
            if (map.get(two) == null) {
                map.put(two, new HashSet<Integer>());
            }
            map.get(one).add(two);
            map.get(two).add(one);
        }
        dfs(0, connected, map);
        boolean allConnected = true;
        for (int i = 0; i < numHouses; i++) {
            if (!connected[i]) {
                io.println(i+1);
                allConnected = false;
            }
        }
        if (allConnected) {
            io.println("Connected");
        }

        io.flush();
        io.close();
    }

    public void dfs(int i, boolean[] connected, HashMap<Integer, HashSet<Integer>> map) {
        if (!connected[i]) {
            connected[i] = true;
            if (map.get(i) != null) {
                for (Integer other : map.get(i)) {
                    dfs(other, connected, map);
                }
            }
        }
    }

    public static void main(String[] args) {
        new WheresMyInternet().go();
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
                } catch (IOException e) {
                }
            return token;
        }

        private String nextToken() {
            String ans = peekToken();
            token = null;
            return ans;
        }
    }
}
