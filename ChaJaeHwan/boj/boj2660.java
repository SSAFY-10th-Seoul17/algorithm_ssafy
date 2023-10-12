import java.io.*;
import java.util.*;
public class boj2660 {

    static int N;
    static ArrayList<Integer>[] graph;
    static int[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        answer = new int[N];

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList();
        }

        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1) {
                break;
            }
            graph[a].add(b);
            graph[b].add(a);
        }
        for (int i = 1; i <= N; i++) {
            answer[i-1] = dfs(i);
        }

        int min = Arrays.stream(answer).min().getAsInt();
        int size = (int) Arrays.stream(answer).filter(a -> a == min).count();
        StringBuilder sb = new StringBuilder();
        sb.append(min).append(" ").append(size).append("\n");
        for (int i = 0; i < N; i++) {
            if (answer[i] == min) {
                sb.append(i+1).append(" ");
            }
        }
        System.out.println(sb);

    }

    static int dfs(int n) {
        boolean[] visited = new boolean[N + 1];
        ArrayDeque<Integer> que = new ArrayDeque();
        que.add(n);
        visited[n] = true;
        int cnt = -1;
        while (!que.isEmpty()) {
            cnt += 1;
            int qsize = que.size();
            for (int i = 0; i < qsize; i++) {
                int poll = que.poll();
                for (int j = 0; j < graph[poll].size(); j++) {
                    int next = graph[poll].get(j);
                    if (!visited[next]) {
                        visited[next] = true;
                        que.add(next);
                    }
                }
            }
        }

        return cnt;
    }
}
