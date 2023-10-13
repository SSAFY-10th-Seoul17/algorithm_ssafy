import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj18243 {

    static int N, K, A, B;
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B);
            graph.get(B).add(A);
        }

        System.out.println(isSmallWorld());


    }

    static String isSmallWorld() {
        for (int i = 1; i <= N; i++) {
            boolean[] visited = new boolean[N + 1];
            Queue<Integer> que = new ArrayDeque<>();
            que.add(i);
            visited[i] = true;
            int depth = 0;
            while (!que.isEmpty()) {
                int qsize = que.size();
                depth += 1;
                for (int j = 0; j < qsize; j++) {
                    int poll = que.poll();
                    for (int node : graph.get(poll)) {
                        if (!visited[node]){
                            visited[node] = true;
                            que.add(node);
                        }
                    }
                }

                if (depth == 6) {
                    break;
                }
            }

            for (int j = 1; j <= N; j++) {
                if (!visited[j]) {
                    return "Big World!";
                }
            }

        }
        return "Small World!";
    }


}
