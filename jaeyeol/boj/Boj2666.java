import java.io.*;
import java.util.StringTokenizer;

public class Boj2666 {
    static int[] sequence;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int open1 = Integer.parseInt(st.nextToken());
        int open2 = Integer.parseInt(st.nextToken());

        int s = Integer.parseInt(br.readLine());
        sequence = new int[s];
        for (int i = 0; i < s; i++) {
            sequence[i] = Integer.parseInt(br.readLine());
        }
        dp = new int[s][n + 1][n + 1];

        System.out.println(dfs(0, Math.min(open1, open2), Math.max(open1, open2)));
    }

    private static int dfs(int depth, int open1, int open2) {
        if (depth >= sequence.length) {
            return 0;
        } else if (dp[depth][open1][open2] > 0) {
            return dp[depth][open1][open2];
        }

        int number = sequence[depth];
        int count = Integer.MAX_VALUE;

        if (number > open1) { // 우측 문을 사용할 수 있는 경우
            count = dfs(depth + 1, open1, number) + Math.abs(number - open2);
        }
        if (number < open2) { // 좌측 문을 사용할 수 있는 경우
            count = Math.min(count, dfs(depth + 1, number, open2) + Math.abs(number - open1));
        }

        return dp[depth][open1][open2] = count;
    }

}


