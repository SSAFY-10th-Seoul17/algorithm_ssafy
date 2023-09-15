import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj14284 {
    static int N, M;
    static Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());
            if (!graph.containsKey(from)) {
                graph.put(from, new HashMap<>());
            }
            graph.get(from).put(to, amount);
            if (!graph.containsKey(to)) {
                graph.put(to, new HashMap<>());
            }
            graph.get(to).put(from, amount);
        }
        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        int answer = 1;
        int[] cur_min = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            cur_min[i] = Integer.MAX_VALUE;
        }
        cur_min[from] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->{
            return (o1[0]!=o2[0])?(o1[0] - o2[0]):o1[1]-o2[1];
        });
        pq.add(new int[]{0, from});
        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            int cur = temp[1], amount = temp[0];
            if (!graph.containsKey(cur)) {
                continue;
            }
            for (int adj : graph.get(cur).keySet()) {
                int hubo = amount + graph.get(cur).get(adj);
                if (cur_min[adj] > hubo) {
                    pq.add(new int[]{hubo, adj});
                    cur_min[adj] = hubo;
                }
            }
        }
        System.out.println(cur_min[to]);
    }

}
