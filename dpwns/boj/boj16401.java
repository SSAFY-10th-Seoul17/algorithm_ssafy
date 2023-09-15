import java.io.*;
import java.util.*;

public class Main {
    public static int[] snacks;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());
        snacks = new int[n];
        st = new StringTokenizer(br.readLine());
        int max = 0;
        for (int i = 0; i < n; i++) {
            snacks[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, snacks[i]);
        }
        System.out.println(findSnackLen(m, max));
        br.close();
    }

    public static int findSnackLen(int m, int ed) {
        int res = 0;
        int st = 1;
        while(st <= ed) {
            int mid = (st + ed) / 2;
            int cnt = 0;

            for (int i = 0; i < snacks.length; i++) {
                cnt += snacks[i] / mid;
            }

            if(cnt >= m) {
                res = mid;
                st = mid + 1;
            }
            else ed = mid - 1;
        }
        return res;
    }
}
