import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj5904_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(solve(N));
    }

    private static String solve(int N) {

        int index = 3;
        int length = 3;

        while (length < N) {
            index++;  // 0 3
            length = length * 2 + index;
        }

        while (length > 3) {
            length = (length - (index)) / 2;

            if (length + index >= N && length < N) {
                return N - length == 1 ? "m" : "o";
            } else if (length < N) {
                N -= length + index;
            }
            index--;
        }
        return N == 1 ? "m" : "o";
    }
}
