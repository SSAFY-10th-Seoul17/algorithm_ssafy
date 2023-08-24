import java.util.*;
import java.io.*;

public class Main {
    static class Node {
        int num, cost;
        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    public static int[] degree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken());
        degree = new int[n + 1];
        Arrays.fill(degree, Integer.MAX_VALUE);
        degree[s] = 0;
        dijkstra(s);
        System.out.println(degree[t]);
        br.close();
    }

    public static void dijkstra(int start) {
        Queue<Node> queue = new PriorityQueue<>((Node n1, Node n2) -> n1.cost - n2.cost);
        queue.offer(new Node(start, 0));
        Set<Integer> set = new HashSet<>();
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            if (set.contains(n.num)) continue;  // 현재노드에서 탐색이 이미 이루어졌음
            set.add(n.num);
            for(Node next : graph.get(n.num)) {
                if (degree[next.num] > degree[n.num] + next.cost) { // 비용이 최소가 되는 경로 업데이트
                    degree[next.num] = degree[n.num] + next.cost;
                    queue.offer(new Node(next.num, degree[next.num]));
                }
            }
        }
    }

}
