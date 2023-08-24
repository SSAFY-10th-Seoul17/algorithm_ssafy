import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj8394 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         N = Integer.parseInt(br.readLine());
        System.out.println(solve(1, 1, 1));
    }

    private static int solve(int count, int before , int current) {

        if(count >= N){
            return current % 10;
        }
        return solve(count + 1, current , (before + current) % 10);
    }
}
