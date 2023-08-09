import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj5014 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[F+1];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{S, 0});

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            if (cur[0] == G) {
                System.out.println(cur[1]);
                return;
            }
            if (visited[cur[0]]) {
                continue;
            }
            visited[cur[0]] = true;

            if (cur[0] + U <= F) {
                queue.offer(new int[]{cur[0] + U, cur[1] + 1});
            }
            if (cur[0] - D > 0) {
                queue.offer(new int[]{cur[0] - D, cur[1] + 1});
            }

        }
        System.out.println("use the stairs");
        return;
    }

}
