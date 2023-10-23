import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj13424 {

    static ArrayList<ArrayList<Node>> map;
    static int[] result;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            map = new ArrayList<>();
            result = new int[N + 1];
            for (int i = 0; i <= N; i++) {
                map.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int distance = Integer.parseInt(st.nextToken());

                map.get(start).add(new Node(end, distance));
                map.get(end).add(new Node(start, distance));
            }

            int K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                solve(Integer.parseInt(st.nextToken()));
            }
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int i = 1; i <= N; i++) {
                if(min > result[i]){
                    min = result[i];
                    minIndex = i ;
                }
            }

            System.out.println(minIndex);
        }
    }

    public static void solve(int startIndex) {

        int[] dp = new int[N + 1];
        Arrays.fill(dp, -1);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(startIndex, 0));

        while (!pq.isEmpty()) {

            Node curNode = pq.poll();

            if (dp[curNode.index] != -1) {
                continue;
            }
            dp[curNode.index] = curNode.distance;

            for (Node nextNode : map.get(curNode.index)) {
                pq.offer(new Node(nextNode.index, nextNode.distance + curNode.distance));
            }
        }
        for(int i = 0; i <= N; i ++){
            result[i] += dp[i];
        }
    }

    private static class Node implements Comparable<Node> {
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return distance - o.distance;
        }
    }
}
