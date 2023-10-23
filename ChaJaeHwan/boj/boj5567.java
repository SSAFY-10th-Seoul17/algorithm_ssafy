import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj5567 {
    static int n, m, answer = 0;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        bfs();

        System.out.println(answer);

    }

    static void bfs() {
        Queue<Integer> que = new ArrayDeque<>();
        que.add(1);
        visited[1] = true;
        int w = 0;
        while (!que.isEmpty()) {
            int qsize = que.size();
            for (int i = 0; i < qsize; i++) {
                int pop = que.poll();
                for (int n : graph[pop]) {
                    if (!visited[n]) {
                        visited[n] = true;
                        que.add(n);
                        answer += 1;
                    }
                }

            }
            w += 1;
            if (w == 2) {
                return;
            }
        }
    }
}
