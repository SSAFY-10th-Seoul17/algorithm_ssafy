import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long N, a, b, c, maxScore;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        beautiful(0, 0, a);
        System.out.println(maxScore);

    }

    public static void beautiful(int white, int dark, long score) {
        if (white == N && dark == N) {
            maxScore = Long.max(maxScore, score);
            return;
        }

        if (white < N) {
            beautiful(white+1, dark, (score+b)%100000);
        }

        if (dark < N && dark < white) {
            beautiful(white, dark+1, (score*c)%100000);
        }
    }

}
