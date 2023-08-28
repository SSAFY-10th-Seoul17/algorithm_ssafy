import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj11729 {

    public static StringBuilder sb = new StringBuilder();
    public static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        hanoi(N, 1, 2, 3);
        System.out.println(cnt);
        System.out.println(sb.toString());

    }

    public static void hanoi(int N, int from, int aux, int to) {
        cnt += 1;
        if (N == 1) {
            sb.append(from + " " + to + "\n");
            return;
        }

        hanoi(N - 1, from, to, aux);
        sb.append(from + " " + to + "\n");
        hanoi(N - 1, aux, from, to);
    }
}
