import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj14699 {
    private static int[] H,  memo;
    private static int N;
    private static Node[] graph;
    private static class Node {
        int v;
        Node next;
        Node(int v, Node next){
            this.v = v;
            this.next = next;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new Node[N+1];
        H = new int[N+1];
        memo = new int[N+1];
        st = new StringTokenizer(br.readLine()," ");
        for (int i = 1; i <= N; i++){
            H[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (H[a] < H[b]){
                graph[a] = new Node(b,graph[a]);
            } else {
                graph[b] = new Node(a,graph[b]);
            }
        }
        Arrays.fill(memo, -1);
        for (int i = 1; i <= N; i++){
            memo[i] = dfs(i);
        }
        for (int i = 1; i <= N; i++){
            System.out.println(memo[i]);
        }
    }

    private static int dfs(int s) {
        if (memo[s] != -1){
            return memo[s];
        }
        memo[s] = 1;
        for (Node p = graph[s]; p != null; p = p.next) {
            memo[s] = Math.max(memo[s], dfs(p.v) + 1);
        }
        return memo[s];
    }
}