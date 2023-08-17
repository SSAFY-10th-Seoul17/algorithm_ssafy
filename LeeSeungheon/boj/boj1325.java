import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj1325 {
    static int M;
    static int N;
    static ArrayList<ArrayList<Integer>> map;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
//        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }
        dp = new int[N + 1];



        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            map.get(start).add(end);
        }

        for (int i = 0; i < N; i++) {
//            solve(i);
        }

    }

//    private static int solve(int start) {
//        PriorityQueue<Node> queue = new PriorityQueue<>();
//        boolean[] visited = new boolean[N + 1];
//        queue.offer(new Node(start, 1));
//
//        while (!queue.isEmpty()) {
//
//            Node curNode = queue.poll();
//            dp[curNode.index] += curNode.count;
//
//            if (visited[curNode.index]) {
//                continue;
//            }
//
//
//            for (int nextNode : map.get(curNode.index)) {
//
//                queue.offer(new Node(nextNode, curNode.count));
//
//
//            }
//
//
//        }
//
//    }

    private static class Node implements Comparable<Node> {
        int index;
        int count;

        public Node(int index, int count) {
            this.index = index;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(count, o.count);
        }
    }

}
