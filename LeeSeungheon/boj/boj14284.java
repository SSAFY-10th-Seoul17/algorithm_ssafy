import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj14284 {

    static ArrayList<ArrayList<Node>> map;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            map.get(A).add(new Node(B, C));
            map.get(B).add(new Node(A, C));
        }
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        System.out.println(solve(S, T));
    }


    private static int solve(int S, int T) {
        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(S, 0));

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();

            if(dp[curNode.index] < curNode.dis){
                continue;
            }
            dp[curNode.index] = curNode.dis;

            for (Node nextNode : map.get(curNode.index)) {
                queue.offer(new Node(nextNode.index, curNode.dis + nextNode.dis));
            }
        }

        return dp[T];
    }

    private static class Node implements Comparable<Node> {
        int index;
        int dis;

        public Node(int index, int dis) {
            this.index = index;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(dis, o.dis);
        }
    }

}
