import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj2792 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] boseok = new int[M];

        for (int num = 0; num < M; num++) {
            int A = Integer.parseInt(br.readLine());
            boseok[num] = A;
        }

        System.out.println(solve(boseok, N));
    }

    private static int solve(int[] boseok, int n) {

        Arrays.sort(boseok);

        int low = 0;
        int high = boseok[boseok.length - 1];
        int mid = 0;

        while (low + 1 < high) {

            mid = (low + high) / 2;

            if (n >= count(mid, boseok)) {
                high = mid;
            } else {
                low = mid;
            }
        }

        return high;
    }

    private static int count(int mid, int[] boseok) {

        int sum = 0;

        for (int bo : boseok) {

            sum += bo / mid;
            if (bo % mid != 0) {
                sum ++;
            }
        }

        return sum;
    }
}
