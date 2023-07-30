import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj4779 {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String N;

        while ((N = br.readLine()) != null) {

            solve((int) Math.pow(3, Integer.parseInt(N) - 1));
            bw.newLine();
            bw.flush();
        }

    }

    private static void solve(int n) throws IOException {

        if (n == 0) {
            bw.write("-");
            return;
        }

        solve(n / 3);

        for (int i = 0; i < n; i++) {
            bw.write(" ");
        }

        solve(n / 3);
    }////..
}
