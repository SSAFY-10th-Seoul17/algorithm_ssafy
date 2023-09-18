import java.io.*;
import java.util.*;

// 개똥벌레
public class boj3020 {
    static int n, h;
    static int[] up;
    static int[] down;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        up = new int[n/2];
        down= new int[n/2];

        int ui = 0;
        int di = 0;

        for (int i = 0; i < n; i++){
            if (i % 2 == 1)
                up[ui++] = Integer.parseInt(br.readLine());
            else
                down[ui] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(up);
        Arrays.sort(down);

        int[] ans = {Integer.MAX_VALUE, 0};

        for (int hNow = 1; hNow <= h; hNow++){
            di = binSearch(false, hNow);
            ui = binSearch(true, h - hNow + 1);
            di = up.length - di;
            ui = up.length - ui;

            // 장애물 값
            int result = di + ui;
            if (result < ans[0]){
                ans[0] = result;
                ans[1] = 1;
            } else if (result == ans[0]){
                ans[1]++;
            }
        }
        System.out.println(new StringBuilder().append(ans[0]).append(" ").append(ans[1]).toString());
    }

    static int binSearch(boolean isup, int key) {
        int[] arr;
        if (isup)
            arr = up;
        else
            arr = down;

        int l = 0;
        int r = arr.length;
        int m;
        // 크거나 같은 수 최고 왼쪽 반환
        while (l < r){
            m = (l + r) / 2;
            if (arr[m] < key)
                l = m + 1;
            else
                r = m;
        }
        return l;
    }
}
