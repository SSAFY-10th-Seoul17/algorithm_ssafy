import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj2229 {

    static int N;
    static int[] students;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        students = new int[N + 1];
        dp = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            students[i] = Integer.parseInt(st.nextToken());
            int max = 0, min = 10001;
            for (int j = i; j > 0; j--) {
                max = Math.max(max, students[j]);
                min = Math.min(min, students[j]);
                dp[i] = Math.max(dp[i], max - min + dp[j - 1]);
            }
        }
//        System.out.println(Arrays.toString(dp));

        System.out.println(dp[N ]);


    }

}
