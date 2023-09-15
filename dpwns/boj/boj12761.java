import java.io.*;
import java.util.*;

public class Main {
    public static final int MAX_LEN = 100_000;
    public static int[] direction, info = new int[4];  // a, b, n, m
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < info.length; i++) {
            info[i] = Integer.parseInt(st.nextToken());
        }
        direction = new int[]{1, -1, info[0], -info[0], info[1], -info[1], info[0], info[1]};
        System.out.println(bfs());

        br.close();
    }

    public static int bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{info[2], 0});
        int[] visited = new int[MAX_LEN+1];
        while (!queue.isEmpty()) {
            int[] loc = queue.poll();
            for (int i = 0; i < direction.length; i++) {
                int next = i < 6 ? loc[0] + direction[i] : loc[0] * direction[i];
                if(next < 0 || next > 100000) continue;
                if(next == info[3]) return loc[1] + 1;
                if(visited[next] == 0) {
                    visited[next] = 1;
                    queue.offer(new int[]{next, loc[1] + 1});
                }
            }
        }
        return -1;
    }

}
