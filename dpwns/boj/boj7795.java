import java.io.*;
import java.util.*;

public class Main {
    public static int[] a, b;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
            a = new int[n];
            b = new int[m];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(b);
            Arrays.sort(a);
            int sum = 0, ed = m-1;
            for (int i = n-1; i >= 0; i--) {
                if(a[i] <= b[0]) continue;
                ed = binarySearch(a[i], ed);
                sum += ed + 1;
            }
            System.out.println(sum);
        }
        br.close();
    }

    public static int binarySearch(int key, int ed) {
        int st = 0, res = ed;
        while (st <= ed) {
            int mid = (st + ed) / 2;
            if(key > b[mid]) {
                res = mid;
                st = mid + 1;
            }
            else ed = mid - 1;
        }
        return res;
    }

}
