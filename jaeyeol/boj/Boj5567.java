import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj5567 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        boolean[][] map = new boolean[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = true;
            map[b][a] = true;
        }

        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (map[1][i]) {
                for (int j = 2; j <= n; j++) {
                    if (map[j] != null && map[j][i]) {
                        map[j] = null;
                        count++;
                    }
                }

                if (map[i] != null) {
                    map[i] = null;
                    count++;
                }
            }
        }

        System.out.println(count);
    }

}

