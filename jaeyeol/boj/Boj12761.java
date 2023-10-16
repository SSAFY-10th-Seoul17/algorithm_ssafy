import java.io.*;
import java.util.*;

public class Boj12761 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        System.out.println(getMinMoveCount(a, b, n, m));
    }

    private static int getMinMoveCount(int a, int b, int start, int end) {
        int[] distances = new int[100_001];
        final int[] move = {-1, 1, a, b, -a, -b};
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int m : move) {
                moveOrJump(queue, distances, current + m, current);
            }
            moveOrJump(queue, distances, current * a, current);
            moveOrJump(queue, distances, current * b, current);

            if (distances[end] > 0) {
                break;
            }
        }

        return distances[end];
    }

    private static void moveOrJump(Queue<Integer> queue, int[] distances, int move, int current) {
        if (move >= 0 && move < distances.length && distances[move] == 0) {
            queue.add(move);
            distances[move] = distances[current] + 1;
        }
    }

}

