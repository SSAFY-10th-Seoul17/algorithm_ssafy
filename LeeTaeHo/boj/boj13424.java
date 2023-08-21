import java.io.*;
import java.util.*;
public class boj13424 {
    static List<List<Node>> graph = new ArrayList<>();
    static int n, m;
    static int INF = Integer.MAX_VALUE;
    static int[][] dist;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int testCase = 0; testCase < t; testCase++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph.get(to).add(new Node(from, weight));
                graph.get(from).add(new Node(to, weight));
            }
            int k = Integer.parseInt(br.readLine());
            int[] startArr = new int[k];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < k; i++){
                startArr[i] = Integer.parseInt(st.nextToken());
            }
            dist = new int[n + 1][n + 1];
            for(int i = 0; i <= n; i++){
                Arrays.fill(dist[i], INF);
            }
            int sum = INF;
            int idx = 0;
            for(int i = 0; i < k; i++){
                dijkstra(startArr[i]);
            }
            for (int i = 1; i <= n; i++) {
                int tempSum = 0;
                for(int j = 0; j < k; j++){
                    if(dist[startArr[j]][i] > 0) tempSum += dist[startArr[j]][i];
                }
                if(tempSum < sum){
                    sum = tempSum;
                    idx = i;
                }
            }
            System.out.println(idx);
            graph.clear();
        }

    }
    public static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start][start] = 0;
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()){
            Node nowNode = pq.poll();
            if(dist[start][nowNode.idx] < nowNode.weight) continue;
            for(Node next : graph.get(nowNode.idx)){
                if(dist[start][nowNode.idx] + next.weight < dist[start][next.idx]){
                    dist[start][next.idx] = dist[start][nowNode.idx] + next.weight;
                    pq.offer(new Node(next.idx , dist[start][next.idx]));
                }
            }
        }
    }
    static class Node implements Comparable<Node>{
        int idx;
        int weight;
        Node(int idx, int weight){
            this.idx = idx;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}