
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class boj17103 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = br.read();
        boolean[] ignore = new boolean[1_000_000];

        for (int i = 2; i < ignore.length; i++) {
            if (ignore[i]) {
                continue;
            }
            for (int j = 2; j * i < ignore.length; j++) {
                ignore[j * i] = true; // false 가 소수
            }
        }

        for (int testCase = 0; testCase < T; testCase++) {
            System.out.println(solve(br.read(), ignore))  ;
        }
    }

    public static int solve(int N, boolean[] ignore) {
        int count = 0;
        for (int i = 2; i <= N / 2; i++) {
            if (!ignore[i] && !ignore[N - i]) {
                count++;
            }
        }
        return count;
    }
}
