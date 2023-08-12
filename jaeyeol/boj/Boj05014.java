import java.io.*;
import java.util.*;

// 스타트링크
public class Boj05014 {
    static final String FAIL_MSG = "use the stairs";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int f = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int u = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        System.out.println(bfs(f, s, g, u, d));
    }

    private static String bfs(int f, int s, int g, int u, int d) {
        int[] distance = new int[f + 1];
        distance[s] = 1;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);

        while (!queue.isEmpty()) {
            Integer cur = queue.poll();

            if (cur - d > 0 && distance[cur - d] == 0) {
                queue.add(cur - d);
                distance[cur - d] = distance[cur] + 1;
            }

            if (cur + u <= f && distance[cur + u] == 0) {
                queue.add(cur + u);
                distance[cur + u] = distance[cur] + 1;
            }

            if (distance[g] > 0) {
                break;
            }
        }

        return distance[g] == 0 ? FAIL_MSG : String.valueOf(distance[g] - 1);
    }

}

