import java.io.*;
import java.util.*;

public class Main {
    public static int[] arr, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[n+1];
        for(int i=1; i<=n; i++) {
            dp[i] = searchLIS(i) + 1;
        }
        System.out.println(n - Arrays.stream(dp).max().getAsInt());
        br.close();
    }

    public static int searchLIS(int end) {
        int max = 0;
        for (int i = 1; i < end; i++) {
            if(max >= i) break;
            if(arr[i] > arr[end]) max = Math.max(max, dp[i]);
        }
        return max;
    }
}
