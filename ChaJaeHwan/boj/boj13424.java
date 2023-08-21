import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj13424 {

    static class Node{
        int index;
        int dist;

        public Node(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }
    }
    static int T, N, M, a, b, c, K;

//    static ArrayList<ArrayList<Node>> graph;

    static int[][] graph;
    static ArrayList<Integer> KS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            graph = new int[N][N];
            Arrays.stream(graph).forEach(g -> Arrays.fill(g, 100001));
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j) {
                        graph[i][j] = 0;
                    }
                }
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken())-1;
                b = Integer.parseInt(st.nextToken())-1;
                c = Integer.parseInt(st.nextToken());

                graph[a][b] = c;
                graph[b][a] = c;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < N; k++) {
                        graph[j][k] = Math.min(graph[j][k] , graph[j][i]+ graph[i][k]);
                    }
                }
            }

            K = Integer.parseInt(br.readLine());
            KS = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                KS.add(Integer.parseInt(st.nextToken())-1);
            }

            int min = Integer.MAX_VALUE;
            int minIdx = -1;

            for (int i = 0; i < N; i++) {
                int sum = 0;
                for (int k : KS) {
                    sum += graph[k][i];
                }
                if (sum < min) {
                    minIdx = i;
                    min = sum;
                } else if (sum == min) {
                    minIdx = Math.min(minIdx, i);
                }
            }
            sb.append(minIdx+1).append("\n");

        }
        System.out.println(sb.toString());
    }

}

