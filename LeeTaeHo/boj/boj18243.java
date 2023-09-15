import java.io.*;
import java.util.*;
public class boj18243 {
    static List<Integer>[] graph;
    static int[][] dist;
    static boolean[] visited;
    static int n;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }
        dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], -1);
            bfs(i);
        }
        boolean flag = true;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j < n; j++){
                if(dist[i][j] > 6 || dist[i][j] == -1){
                    flag = false;
                    break;
                }
            }
        }
        if(flag){
            System.out.println("Small World!");
        }else{
            System.out.println("Big World!");
        }
    }
    public static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        visited = new boolean[n + 1];
        q.offer(start);
        dist[start][start] = 0;
        visited[start] = true;
        while (!q.isEmpty()){
            int node = q.poll();
            for (int next : graph[node]) {
                if(!visited[next]){
                    dist[start][next] = dist[start][node] + 1;
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }
    }
}
