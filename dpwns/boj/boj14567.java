import java.io.*;
import java.util.*;


public class Main {

    public static List<ArrayList<Integer>> graph = new ArrayList<>();
    public static int[] degree, res;
    public static Queue<Integer> queue = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        degree = new int[n+1];
        res = new int[n+1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            degree[b]++;
        }
        for (int i = 1; i <= n; i++) {
            if(degree[i] == 0) {
                queue.offer(i);
                res[i] = 1;
            }
        }
        dfs();
        StringBuilder sb = new StringBuilder();
        Arrays.stream(res, 1, res.length).forEach(r -> sb.append(r).append(" "));
        System.out.println(sb);
        br.close();
    }

    public static void dfs() {
        while (!queue.isEmpty()) {
            int subject = queue.poll();
            for (int next : graph.get(subject)) {
                res[next] = res[subject] + 1;
                if(--degree[next] == 0) queue.offer(next);
            }
        }
    }
}
