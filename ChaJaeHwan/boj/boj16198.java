import java.util.*;
import java.io.*;

public class boj16198 {

    static int N;

    static int[] W;

    static boolean[] used;

    static int max = -1;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        W = new int[N];
        used = new boolean[N];

        for (int i = 0; i < N; i++) {
            W[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);
        System.out.println(max);

    }

    static void dfs(int depth, int sum) {
        if (depth == N - 2) {
            max = Math.max(max, sum);
        }
        for (int i = 1; i < N - 1; i++) {
            if (!used[i]) {
                used[i] = true;
                int left = 1;
                for (int j = i - 1; j >= 0; j--) {
                    if (!used[j]) {
                        left = W[j];
                        break;
                    }
                }
                int right = 1;
                for (int j = i + 1; j < N; j++) {
                    if (!used[j]) {
                        right = W[j];
                        break;
                    }
                }
                dfs(depth + 1, sum + left * right);
                used[i] = false;
            }
        }
    }
}
