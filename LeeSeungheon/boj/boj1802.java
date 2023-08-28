import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1802 {
    static String s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            s = br.readLine();
            sb.append(solve(0, s.length() - 1) ? "YES" : "NO").append("\n");
        }
        System.out.println(sb);
    }

    private static boolean solve(int start, int end) {

        if (end == start) {
            return true;
        }

        int left = start;
        int right = end;

        while (++left < --right){
            if (s.charAt(left) == s.charAt(right)) {
                return false;
            }
        }

        if (end - start == 2) {
            if (s.charAt(start) != s.charAt(end)) {
                return true;
            }
            return false;
        }
        return solve(start, end / 2 - 1);
    }


}
