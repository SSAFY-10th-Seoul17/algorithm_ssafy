import java.io.*;
import java.util.*;
public class boj14567 {
    static List<Integer>[] graph;
    static int n;
    static int[] indegree, rank;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        indegree = new int[n + 1];
        rank = new int[n + 1];
        Arrays.fill(rank, 1);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            indegree[to]++;
        }
        topological();
        for (int i = 1; i <= n; i++) {
            sb.append(rank[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
    public static void topological(){
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if(indegree[i] == 0){
                q.offer(i);
            }
        }
        while (!q.isEmpty()){
            int node = q.poll();
            for (int next : graph[node]) {
                if(--indegree[next] == 0){
                    rank[next] = rank[node] + 1;
                    q.offer(next);
                }
            }
        }
    }
}
