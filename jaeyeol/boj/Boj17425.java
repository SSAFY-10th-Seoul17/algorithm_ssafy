import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj17425 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        long[] sum = new long[1_000_001];
        for (int i = 1; i < sum.length; i++) {
            sum[i] += sum[i - 1];
            for (int j = i; j < sum.length; j += i) {
                sum[j] += i;
            }
        }

        StringBuilder result = new StringBuilder();
        while (t-- > 0) {
            result.append(sum[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.println(result);
    }

}


