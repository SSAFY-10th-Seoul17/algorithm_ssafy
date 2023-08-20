import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2004 {

    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int twos = two(n) - two(n - m) - two(m);
        int fives = five(n) - five(n - m) - five(m);

        System.out.println(Math.min(twos, fives));
    }

    static int two(int n) {
        int cnt = 0;

        while (n >= 2) {
            cnt += n / 2;
            n /= 2;
        }
        return cnt;
    }

    static int five(int n) {
        int cnt = 0;

        while (n >= 5) {
            cnt += n / 5;
            n /= 5;
        }
        return cnt;
    }
}
