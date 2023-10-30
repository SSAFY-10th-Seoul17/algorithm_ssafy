import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[][] nums = new int[n][3];
        int[][] minDp = new int[n][3];
        int[][] maxDp = new int[n][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기화
        for (int i = 0; i < 3; i++) {
            minDp[0][i] = nums[0][i];
            maxDp[0][i] = nums[0][i];
        }

        for (int i = 1; i < n; i++) {
            minDp[i][0] = nums[i][0] + Math.min(minDp[i-1][0], minDp[i-1][1]);
            minDp[i][1] = nums[i][1] + Math.min(Math.min(minDp[i-1][0], minDp[i-1][1]), minDp[i-1][2]);
            minDp[i][2] = nums[i][2] + Math.min(minDp[i-1][1], minDp[i-1][2]);

            maxDp[i][0] = nums[i][0] + Math.max(maxDp[i-1][0], maxDp[i-1][1]);
            maxDp[i][1] = nums[i][1] + Math.max(Math.max(maxDp[i-1][0], maxDp[i-1][1]), maxDp[i-1][2]);
            maxDp[i][2] = nums[i][2] + Math.max(maxDp[i-1][1], maxDp[i-1][2]);
        }

        System.out.println(Math.max(Math.max(maxDp[n-1][0], maxDp[n-1][1]), maxDp[n-1][2]) + " " + Math.min(Math.min(minDp[n-1][0], minDp[n-1][1]), minDp[n-1][2]));


    }
}
