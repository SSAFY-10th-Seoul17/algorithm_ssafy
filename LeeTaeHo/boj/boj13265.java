import java.io.*;
import java.util.*;
public class boj13265 {
    static List<List<Integer>> graph;
    static int n, m;
    static int[] color;
    static boolean flag;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            color = new int[n + 1];
            flag = false;

            graph = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph.get(from).add(to);
                graph.get(to).add(from);
            }

            for (int i = 1; i <= n; i++) {
                if(color[i] == 0) bfs(i);
            }

            if(flag){
                sb.append("impossible").append("\n");
            }else{
                sb.append("possible").append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        color[start] = 1;
        while (!q.isEmpty()){
            int node = q.poll();
            for (int next : graph.get(node)) {
                if(color[next] == 0){
                    if(color[node] == 1){
                        color[next] = 2;
                        q.offer(next);
                    } else if (color[node] == 2) {
                        color[next] = 1;
                        q.offer(next);
                    }
                } else if (color[next] == 1 && color[node] != 2) {
                    flag = true;
                    return;
                } else if (color[next] == 2 && color[node] != 1) {
                    flag = true;
                    return;
                }
            }
        }
    }
}
