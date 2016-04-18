import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Squawk {

    Kattio io;

    public void go() {
        io = new Kattio(System.in);
        int numUsers = io.nextInt();
        int numLinks = io.nextInt();
        int initial = io.nextInt();
        int time = io.nextInt();
        Vertex[] users = new Vertex[numUsers];
        long[] toAdd = new long[numUsers];
        boolean[] isSource = new boolean[numUsers];
        for (int i = 0; i < numUsers; i++) {
            users[i] = new Vertex(i);
        }
        isSource[initial] = true;
        users[initial].squawks = 1;
        toAdd[initial] = 1;
        for (int i = 0; i < numLinks; i++) {
            int one = io.nextInt();
            int two = io.nextInt();
            users[one].add(users[two]);
        }
        long sum = 1;
        for (int t = 0; t < time; t++) {
            for (int i = 0; i < numUsers; i++) {
                if (!isSource[i]) {
                    continue;
                }
                for (Vertex other : users[i].others) {
                    other.squawks += toAdd[i];
                }
            }
            sum = 0;
            for (int i = 0; i < numUsers; i++) {
                isSource[i] = users[i].squawks != 0;
                users[i].squawks -= toAdd[i];
                toAdd[i] = users[i].squawks;
                sum += users[i].squawks;
            }
        }
        io.println(sum);

        io.flush();
        io.close();
    }

    public static void main(String[] args) {
        new Squawk().go();
    }

    private class Vertex {

        int index;
        long squawks = 0;
        HashSet<Vertex> others;

        public Vertex(int i) {
            index = i;
            others = new HashSet<>();
        }

        public void add(Vertex other) {
            others.add(other);
            other.others.add(this);
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
