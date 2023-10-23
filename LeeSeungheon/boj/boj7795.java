import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj7795 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] A = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            int[] B = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            sb.append(solve(A, B)).append("\n");

        }
        System.out.println(sb);
    }

    private static int solve(int[] A, int[] B) {
        int sum = 0;
        Arrays.sort(A);

        for (int b = 0; b < B.length; b++) {

            int small = 0;
            int large = A.length - 1;
            int mid = 0;

            while (small <= large) {

                 mid = (small + large) / 2;

                if (A[mid] > B[b]) {
                    large = mid-1;
                } else if (A[mid] <= B[b]) {
                    small = mid+1;
                }
            }
            sum += A.length - small;
        }
        return sum;
    }
}
