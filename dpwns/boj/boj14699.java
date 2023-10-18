import java.util.*;
import java.io.*;

public class Main {
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static int[] height, depth;
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        height = new int[n+1];
        depth = new int[n+1];
        Arrays.fill(depth, 1);
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());

            if(height[a] > height[b]) graph.get(b).add(a);
            else graph.get(a).add(b);
        }

        for (int i = 1; i <= n; i++) {
            if(depth[i] == 1) dfs(i);
        }
        for (int i = 1; i <= n; i++) {
            System.out.println(depth[i]);
        }
        br.close();
    }

    public static int dfs(int node) {
        if(depth[node] > 1) return depth[node];
        for (int next : graph.get(node)) {
            depth[node] = Math.max(dfs(next)+1, depth[node]);
        }
        return depth[node];
    }

}
