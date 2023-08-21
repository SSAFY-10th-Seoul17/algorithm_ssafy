import java.util.*;
import java.io.*;

class Main {
    public static int max = 0, cnt = 0;
    public static List<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            graph.get(b).add(a);
        }
        int[] result = new int[n+1];
        for(int i=1; i<=n; i++) {
            int[] visited = new int[n+1];
            visited[i] = 1;
            cnt = 0;
            dfs(i, visited);
            result[i] = cnt;
            max = Math.max(max, cnt);
        }
        for(int i=1; i<=n; i++) {
            if(max == result[i]) System.out.print(i + " ");
        }
        br.close();
    }
    public static void dfs(int start, int[] visited) {
        for(int next : graph.get(start)) {
            if(visited[next] == 0) {    // 사이클이 있을 수 있음
                visited[next] = 1;
                cnt++;
                dfs(next, visited);
            }
        }
    }
}