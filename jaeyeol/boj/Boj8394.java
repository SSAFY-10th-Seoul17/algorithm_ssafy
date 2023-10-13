import java.io.*;

public class Boj8394 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int previous1 = 0;
        int previous2 = 1;
        int current = 1;

        for (int i = 1; i <= n; i++) {
            current = (previous1 + previous2) % 10;
            previous1 = previous2;
            previous2 = current;
        }

        System.out.println(current);
    }
}
