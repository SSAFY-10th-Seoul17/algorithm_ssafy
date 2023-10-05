import java.util.*;
import java.io.*;

public class Main {
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static int[] depth, visited;
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        depth = new int[n+1];
        visited = new int[n+1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            if(a == -1) break;

            graph.get(a).add(b);
            graph.get(b).add(a);

        }

        for (int i = 1; i <= n; i++) {
            depth[i] = bfs(i);
        }

        StringBuilder sb = new StringBuilder();
        Queue<Integer> res = new ArrayDeque<>();
        int min = n;
        for (int i = 1; i <= n; i++) {
            if(min > depth[i]) {
                min = depth[i];
                res.clear();
                res.offer(i);
            }
            else if(min == depth[i]) res.offer(i);
        }
        sb.append(min).append(" ").append(res.size()).append("\n");
        while (!res.isEmpty()) {
            sb.append(res.poll()).append(" ");
        }
        System.out.println(sb);
        br.close();
    }

    public static int bfs(int node) {
        Queue<Integer> queue = new ArrayDeque<>();
        Arrays.fill(visited, 0);
        visited[node] = 1;
        queue.offer(node);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int n = queue.poll();
                for (int next : graph.get(n)) {
                    if (visited[next] == 1) continue;
                    visited[next] = 1;
                    queue.offer(next);
                }
            }
            depth++;
        }
        return depth - 1;
    }
}
