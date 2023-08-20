import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2503 {

    public static int N;
    public static boolean[] baseball = new boolean[1000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        for (int i = 123; i <= 987; i++) {
            String num = Integer.toString(i);
            if (num.charAt(0) == '0' || num.charAt(1) == '0' || num.charAt(2) == '0') {
                continue;
            }
            if (num.charAt(0) == num.charAt(1) || num.charAt(1) == num.charAt(2) || num.charAt(0) == num.charAt(2)) {
                continue;
            }
            baseball[i] = true;
        }

        for (int t = 0; t < N; t++) {
            st = new StringTokenizer(br.readLine());
            String num = st.nextToken();
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());

            for (int i = 123; i <= 987; i++) {
                if (baseball[i]) {
                    int s = 0;
                    int b = 0;

                    for (int j = 0; j < 3; j++) {
                        char c = Integer.toString(i).charAt(j);
                        for (int k = 0; k < 3; k++) {
                            if (c == num.charAt(k) && j == k) {
                                s += 1;
                            } else if (c == num.charAt(k) && j != k) {
                                b += 1;
                            }
                        }
                    }

                    if (s != strike || b != ball) {
                        baseball[i] = false;
                    }
                }
            }
        }
        int cnt = 0;
        for (int i = 123; i <= 987; i++) {
            if (baseball[i]) {
                cnt += 1;
            }
        }
        System.out.println(cnt);
    }
}
