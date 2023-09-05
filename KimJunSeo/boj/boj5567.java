import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj5567 {
    static HashMap<Integer, List<Integer>> graph = new HashMap<>();
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if (!graph.containsKey(from)) {
                graph.put(from, new ArrayList<>());
            }
            graph.get(from).add(to);
            if (!graph.containsKey(to)) {
                graph.put(to, new ArrayList<>());
            }
            graph.get(to).add(from);
        }
        int answer = 0;
        Deque<int[]> deque = new ArrayDeque();
        deque.add(new int[]{1, 0});
        boolean[] visited = new boolean[N+1];
        visited[1]=true;
        while (!deque.isEmpty()) {
            int[] temp = deque.pollFirst();
            int friend = temp[0], level = temp[1];
            if (graph.containsKey(friend)) {
                for (int adj : graph.get(friend)) {
                    if (!visited[adj] && level+1<=2){
                        visited[adj]=true;
                        answer++;
                        deque.add(new int[]{adj, level + 1});
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
