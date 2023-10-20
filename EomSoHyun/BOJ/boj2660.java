import java.io.*;
import java.util.*;

public class Main {

    static int n, minScore, minCnt;
    static List<Integer> result;
    static List<Integer>[] candi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());  // 회원의 수
        candi = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            candi[i] = new ArrayList<>();
        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            if (v1 == -1) break;

            candi[v1-1].add(v2-1);
            candi[v2-1].add(v1-1);
        }

        minScore = Integer.MAX_VALUE;
        minCnt = Integer.MAX_VALUE;
        result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int score = bfs(i);
            if (score < minScore) {
                minCnt = 1;
                minScore = score;
                result.clear();
                result.add(i+1);
            }
            else if (score == minScore) {
                minCnt++;
                result.add(i+1);
            }
        }

        sb.append(minScore).append(" ").append(minCnt).append("\n");
        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i)).append(" ");
        }

        System.out.println(sb);

    }

    public static int bfs(int x) {
        boolean[] visited = new boolean[n];
        visited[x] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x, 0});
        int minCnt = 0;

        while (!q.isEmpty()) {
            int v[] = q.poll();
            for (Integer w: candi[v[0]]) {
                if (visited[w]) continue;
                visited[w] = true;
                q.offer(new int[] {w, v[1] + 1});
                if (minCnt < v[1] + 1) {
                    minCnt = v[1] + 1;
                }
            }
        }

        return minCnt;


    }
}
