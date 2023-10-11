import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj2660 {
    static class Node {
        int v;
        Node next;
        Node(int v, Node next){
            this.v = v;
            this.next = next;
        }
    }
    private static Node[] m;
    private static int n;
    private static int[] res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        m = new Node[n+1];
        res = new int[n+1];
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1) break;
            m[a] = new Node(b,m[a]);
            m[b] = new Node(a,m[b]);
        }
        int cnt = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++){
            bfs(i);
            if (min > res[i]){
                min = res[i];
                cnt = 1;
            } else if (min == res[i]){
                cnt++;
            }
        }
        sb.append(min).append(" ").append(cnt).append("\n");

        for (int i = 1; i <= n; i++){
            if (res[i] == min){
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb.toString());
    }
    
    private static void bfs(int s){
        int[] visited = new int[n+1];
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(s);
        while (!q.isEmpty()){
            int cur = q.poll();
            for (Node p = m[cur]; p != null; p = p.next){
                if (visited[p.v] != 0) continue;
                visited[p.v] = visited[cur] + 1;
                q.offer(p.v);
            }
        }
        for (int i = 1; i <= n; i++) {
            if (i == s) continue;
            if (res[s] < visited[i]) {
                res[s] = visited[i];
            }
        }
    }
}
