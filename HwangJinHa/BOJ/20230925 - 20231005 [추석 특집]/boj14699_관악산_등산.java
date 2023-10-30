package study10월1주차목;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj14699_관악산_등산 {
    static int n, m;
    static int[] height;
    static ArrayList<Integer>[] graph;
    static int[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        height = new int[n+1];
        graph = new ArrayList[n+1];
        ans = new int[n+1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            height[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (height[a] < height[b]){
                graph[a].add(b);
            }
            else if (height[b] < height[a]){
                graph[b].add(a);
            }
        }

        for (int i = 1; i <= n; i++) {
            if (ans[i] == 0) {
                dfs(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(ans[i]);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int dfs(int node) {
        if (ans[node] != 0)
            return  ans[node];
        int max = 0;
        for (int next : graph[node]) {
            int result = dfs(next);
            if (result > max)
                max = result;
        }
        return ans[node] = max + 1;
    }
}