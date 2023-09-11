import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj12761 {
    static final int MAX_STONE = 100_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        solve(A, B, N, M);
        System.out.println(sb);
    }

    private static void solve(int a, int b, int n, int m) {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[MAX_STONE + 1];
        queue.offer(new Node(n, 0));

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();

            if (visited[curNode.index]) {
                continue;
            }
            visited[curNode.index] = true;

            if (curNode.index == m) {
                System.out.println(curNode.count);
                return;
            }

            curNode.count++;

            if (curNode.index * a <= MAX_STONE) {
                queue.offer(new Node(curNode.index * a, curNode.count));
            }
            if (curNode.index * b <= MAX_STONE) {
                queue.offer(new Node(curNode.index * b, curNode.count));
            }
            if (curNode.index + b <= MAX_STONE) {
                queue.offer(new Node(curNode.index + b, curNode.count));
            }
            if (curNode.index - b >= 0) {
                queue.offer(new Node(curNode.index - b, curNode.count));
            }
            if (curNode.index + a <= MAX_STONE) {
                queue.offer(new Node(curNode.index + a, curNode.count));
            }
            if (curNode.index - a >= 0) {
                queue.offer(new Node(curNode.index - a, curNode.count));
            }
            if (curNode.index + 1 <= MAX_STONE) {
                queue.offer(new Node(curNode.index + 1, curNode.count));
            }
            if (curNode.index - 1 >= 0) {
                queue.offer(new Node(curNode.index - 1, curNode.count));
            }


        }

    }

    private static class Node {

        int index, count;

        public Node(int index, int count) {
            this.index = index;
            this.count = count;
        }
    }

}
