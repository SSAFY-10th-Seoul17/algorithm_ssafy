package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
돌다리
 */
public class boj12761 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] dx = {-1, 1, a, b, -a, -b};

        System.out.println(bfs(n, m, dx));

    }

    private static int bfs(int n, int m, int dx[]) {
        Queue<Integer> q = new ArrayDeque<Integer>();
        int[] min = new int[100001];

        Arrays.fill(min, 100002);

        q.add(n);
        min[n] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 0; i < 6; i++) {
                int cmp = cur + dx[i];
                if (cmp >= 0 && cmp <= 100000 && min[cmp] > min[cur] + 1) {
                    min[cmp] = min[cur] + 1;
                    q.add(cmp);
                }
            }

            if (dx[2] * cur >= 0 && dx[2] * cur <= 100000 && min[dx[2] * cur] > min[cur] + 1) {
                min[dx[2] * cur] = min[cur] + 1;
                q.add(dx[2] * cur);
            }

            if (dx[3] * cur >= 0 && dx[3] * cur <= 100000 && min[dx[3] * cur] > min[cur] + 1) {
                min[dx[3] * cur] = min[cur] + 1;
                q.add(dx[3] * cur);
            }

        }
        return min[m];
    }
}

