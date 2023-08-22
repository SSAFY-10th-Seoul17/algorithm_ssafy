import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj16947 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        // input end

        int[] distances = new int[n + 1];
        Queue<Integer> queue = new ArrayDeque<>();

        findLeaf(graph, distances, queue);
        setDistance(graph, distances, queue);

        System.out.println(getResult(n, distances));
    }

    private static StringBuilder getResult(int n, int[] distances) {
        StringBuilder result = new StringBuilder(n * 2);
        for (int i = 1; i <= n; i++) {
            result.append(distances[i]).append(" ");
        }
        return result;
    }

    private static void setDistance(List<List<Integer>> graph, int[] distances, Queue<Integer> queue) {
        for (int i = 1; i < distances.length; i++) {
            if (distances[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph.get(cur)) {
                if (distances[next] == -1) {
                    distances[next] = distances[cur] + 1;
                    queue.add(next);
                }
            }
        }
    }

    private static void findLeaf(List<List<Integer>> graph, int[] distances, Queue<Integer> queue) {
        int[] edgeCounts = new int[graph.size()];

        for (int i = 1; i < graph.size(); i++) {
            edgeCounts[i] = graph.get(i).size();
            if (edgeCounts[i] == 1) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            distances[cur] = -1; // leaf

            for (int next : graph.get(cur)) {
                edgeCounts[next]--;
                if (edgeCounts[next] == 1) {
                    queue.add(next);
                }
            }
        }

    }

}
