import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj14567 {

    static int N, M, A, B;

    static ArrayList<Integer>[] graph;

    static int[] degree;
    static int[] semester;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        degree = new int[N + 1];
        semester = new int[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
            degree[B] += 1;
        }

        topologySort();

    }

    static void topologySort() {
        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                que.add(i);
                semester[i] = 1;
            }
        }

        while (!que.isEmpty()) {

            int poll = que.poll();
            for (int next : graph[poll]) {
                degree[next] -= 1;
                if (degree[next] == 0) {
                    semester[next] = semester[poll] + 1;
                    que.add(next);
                }

            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(semester[i]).append(" ");
        }

        System.out.println(sb);
    }


}
