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
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken()), l1 = Integer.parseInt(st.nextToken()), l2 = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n-1; i++) {   // 간선 입력
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
        int[] d1 = new int[n+1], d2 = new int[n+1];
        dijkstra(l1, d1);   // l1에서 가는 노드들의 최소 비용
        dijkstra(l2, d2);   // l2에서 가는 노드들의 최소 비용

        int min = l1 == l2 ? 0 : Integer.MAX_VALUE;
        for(int i=1; i<=n; i++) {
            for(Node node : graph.get(i)) {
                if (min > d2[node.num] + d1[i]) {
                    min = d2[node.num] + d1[i];
                }
            }
        }
        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
        br.close();
    }

    public static void dijkstra(int start, int[] d) {
        Arrays.fill(d, Integer.MAX_VALUE);
        d[start] = 0;
        Queue<Node> queue = new PriorityQueue<>((Node n1, Node n2) -> n1.cost - n2.cost);
        queue.offer(new Node(start, 0));
        Set<Integer> set = new HashSet<>();
        while(!queue.isEmpty()) {
            Node n = queue.poll();
            if(set.contains(n.num)) continue;
            set.add(n.num);
            for(Node next : graph.get(n.num)) {
                if(d[next.num] > d[n.num] + next.cost) {
                    d[next.num] = d[n.num] + next.cost;
                    queue.offer(new Node(next.num, d[next.num]));
                }
            }
        }
    }
}
