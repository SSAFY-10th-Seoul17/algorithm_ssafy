import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long[] A = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < A.length; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        int s = 0;
        int e = n - 1;
        int m = s + 1;
        long cnt = 0;
        while (m < e) {
            if (A[s] + A[m] + A[e] < 0) {
                m++;
            }
            else if (A[s] + A[m] + A[e] > 0) {
                e--;
            }
            else {
                if (A[m] == A[e]) {
                    cnt += ((long) (e - m + 1) * (e-m) / 2);
                    m = e;
                }

                else {
                    long lCnt = 1;
                    long rCnt = 1;
                    long lNum = A[m];
                    long rNum = A[e];

                    while (m++ < e) {
                        if (A[m] == lNum) {
                            lCnt++;
                        } else {
                            break;
                        }
                    }

                    while (m < e--) {
                        if (A[e] == rNum) {
                            rCnt++;
                        } else {
                            break;
                        }
                    }
                    cnt += lCnt * rCnt;

                }

            }

            if (m >= e) {
                s++;
                m = s + 1;
                e = n - 1;
            }
        }

        System.out.println(cnt);

    }
}
