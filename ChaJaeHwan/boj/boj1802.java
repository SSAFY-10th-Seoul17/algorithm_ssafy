import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1802 {

    static int T;
    static String s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            s = br.readLine();
            if (dongho(0, s.length() - 1)) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }

        System.out.println(sb.toString());

    }

    static boolean dongho(int start, int end) {
        if (start == end) {
            return true;
        }
        int mid = (start + end) / 2;

        for (int i = start; i < mid; i++) {
            if (s.charAt(i) == s.charAt(end - i)) {
                return false;
            }
        }
        return dongho(start, mid - 1) && dongho(mid + 1, end);
    }
}
