import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2508 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();
        while (t-- > 0) {
            br.readLine();
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            char[][] box = new char[n][m];

            for (int i = 0; i < n; i++) {
                String str = br.readLine();
                for (int j = 0; j < m; j++) {
                    box[i][j] = str.charAt(j);
                }
            }

            result.append(getCandies(box)).append("\n");
        }

        System.out.println(result);
    }

    private static int getCandies(char[][] box) {
        int candy = 0;

        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[i].length; j++) {
                if (box[i][j] == CANDY_TYPE[4] && isCandy(box, i, j)) {
                    candy++;
                }
            }
        }

        return candy;
    }

    static final char[] CANDY_TYPE = {'>', '<', 'v', '^', 'o'};

    private static boolean isCandy(char[][] box, int i, int j) {
        if (j > 0 && j < box[i].length - 1) {
            if (box[i][j - 1] == CANDY_TYPE[0] && box[i][j + 1] == CANDY_TYPE[1]) {
                return true;
            }
        }

        if (i > 0 && i < box.length - 1) {
            return box[i - 1][j] == CANDY_TYPE[2] && box[i + 1][j] == CANDY_TYPE[3];
        }

        return false;
    }

}
