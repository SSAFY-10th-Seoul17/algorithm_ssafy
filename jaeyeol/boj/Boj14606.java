import java.io.*;

public class Boj14606 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(dfs(n));
    }

    private static int dfs(int n) {
        if (n == 1) {
            return 0;
        }
        int happy = n / 2 * (n - n / 2);
        return happy + dfs(n / 2) + dfs(n - n / 2);
    }
}
