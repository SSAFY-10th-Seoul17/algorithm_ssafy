import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {

    static class Node implements Comparable<Node> {

        int v;
        int e;

        public Node(int v, int e) {
            this.v = v;
            this.e = e;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.e, o.e);
        }


    }

    static int n;
    static int[] friend;
    static List<List<Node>> map;
    static int[] dist, minDist;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        friend = new int[3];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            friend[i] = Integer.parseInt(st.nextToken());
        }

        map = new ArrayList<List<Node>>();
        for (int i = 0; i < n+1; i++) {
            map.add(new ArrayList<Node>());
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            map.get(v1).add(new Node(v2, e));
            map.get(v2).add(new Node(v1, e));
        }

        minDist = new int[n+1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        for (int i = 0; i < 3; i++) {
            dist = new int[n+1];
            pq = new PriorityQueue<>();
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[friend[i]] = 0;
            pq.offer(new Node(friend[i], 0));
            dijkstra();

            for (int j = 1; j < dist.length; j++) {
                if (dist[j] == 0) continue;
                minDist[j] = Integer.min(minDist[j], dist[j]);
            }


        }

        int maxDist = 0;
        int num = 0;
        for (int i = 1; i < minDist.length; i++) {
            if (maxDist < minDist[i]) {
                maxDist = minDist[i];
                num = i;
            }
        }

        System.out.println(num);

    }

    public static void dijkstra() {

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            for (Node w: map.get(node.v)) {
                if (dist[node.v] + w.e < dist[w.v]) {
                    dist[w.v] = dist[node.v] + w.e;
                    pq.offer(new Node(w.v, dist[w.v]));
                }
            }
        }
    }

}
