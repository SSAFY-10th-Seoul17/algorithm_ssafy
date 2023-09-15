import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [boj] 2556. 별 찍기 - 14
 */
public class boj2556 {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append("*");
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }
}
