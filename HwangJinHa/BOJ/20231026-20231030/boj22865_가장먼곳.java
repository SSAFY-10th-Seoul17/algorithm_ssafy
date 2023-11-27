package study10월5주차월;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj22865_가장먼곳 {
    static int n, a, b, c;
    static int m;

    static int ri = 0;
    static int[][] results;
    static ArrayList<int[]>[] graph;

    static PriorityQueue<int[]> closest = new PriorityQueue<>((a, b) -> {
        if (a[1] == b[1]) {
            if (a[0] < b[0]) {
                return -1;
            }
            else {
                return 1;
            }
        }
        else if (a[1] < b[1]) {
            return -1;
        }
        else {
            return 1;
        }
    });

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken()) - 1;
        b = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        results = new int[3][n];
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int l = Integer.parseInt(st.nextToken());

            graph[d].add(new int[] {e, l});
            graph[e].add(new int[] {d, l});
        }
        dijk(a);
        dijk(b);
        dijk(c);
        int ansidx = -1;
        int anslen = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (i == a || i == b || i == c)
                continue;
            int len = Integer.MAX_VALUE;
            for (int rr = 0; rr < 3; rr++)
                len = Math.min(len, results[rr][i]);
            if (len > anslen) {
                ansidx = i;
                anslen = len;
            }
        }
        System.out.println(ansidx + 1);
    }

    static void dijk(int node) {
        int[] di = results[ri++];
        Arrays.fill(di, Integer.MAX_VALUE);
        di[node] = 0;
        closest.add(new int[] {node, 0});

        boolean[] fixed = new boolean[n];
        while (!closest.isEmpty()) {
            int[] next = closest.poll();
            if (fixed[next[0]])
                continue;
            fixed[next[0]] = true;
            for (int[] nn : graph[next[0]]) {
                int len = di[next[0]] + nn[1];
                if (len < di[nn[0]]) {
                    di[nn[0]] = len;
                    closest.add(new int[] {nn[0], len});
                }
            }
        }
    }
}