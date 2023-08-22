import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj17616 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        List<List<Integer>> winGraph = new ArrayList<>(n+1);
        List<List<Integer>> loseGraph = new ArrayList<>(n+1);
        for (int i = 0; i <= n; i++) {
            winGraph.add(new ArrayList<>());
            loseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int winner = Integer.parseInt(st.nextToken());
            int loser = Integer.parseInt(st.nextToken());

            winGraph.get(winner).add(loser);
            loseGraph.get(loser).add(winner);
        }

        System.out.println(getMaxMinRank(winGraph, loseGraph, n, x));
    }

    // 가장 높은 등수, 낮은 등수
    private static String getMaxMinRank(List<List<Integer>> winGraph, List<List<Integer>> loseGraph, int n, int x) {
        return dfs(loseGraph, new boolean[n + 1], x) + " " + (n - (dfs(winGraph, new boolean[n + 1], x) - 1));
    }

    private static int dfs(List<List<Integer>> graph, boolean[] visited, int x) {
        int sum = 1;
        visited[x] = true;

        for (int node : graph.get(x)) {
            if (!visited[node]) {
                sum += dfs(graph, visited, node);
            }
        }

        return sum;
    }

}
