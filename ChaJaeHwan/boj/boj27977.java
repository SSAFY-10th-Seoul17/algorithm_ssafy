import java.io.*;
import java.util.*;

public class boj27977 {
    static int L, N, K;
    static int[] stations;
    static boolean[] visited;

    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        stations = new int[N];
        st = new StringTokenizer(br.readLine());
        int cur = 0;
        for (int i = 0; i < N; i++) {
            stations[i] = Integer.parseInt(st.nextToken());
            min = Math.max(min, stations[i] - cur);
            cur = stations[i];
        }
        min = Math.max(min, L - cur);
        int l = min;
        int r = L;

        int ret = 0;
        while (l <= r) {
            int m = (l + r) / 2;

            int now = m;
            int pos = 0;
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if (stations[i] - pos > now) {
                    cnt++;
                    now = m;
                }
                now -= (stations[i] - pos);
                pos = stations[i];
            }

            if (cnt <= K) {
                ret = m;
                r = m - 1;
            }
            else {
                l = m + 1;
            }
        }

        System.out.println(ret);

    }

}
