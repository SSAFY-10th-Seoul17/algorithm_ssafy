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

        int index = 0;
        int length = 3;

        while (length < N) {
            index++;  // 0 3
            length = length * 2 + index + 3;
        }

        while (length > 3) {
            length = (length - (index + 3)) / 2;

            if (length + index + 3 >= N && length < N ) {
                return N - length == 1 ? "m" : "o";
            }else if(length < N){
                N -= length + index + 3;
            }
            index--;
        }
        return N == 1 ? "m" : "o";
    }
}
