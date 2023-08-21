import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [boj] 10974.모든 순열
 */
public class boj10974 {
    static int n;
    static int[] numbers;
    static boolean[] isSelected;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        numbers = new int[n];
        isSelected = new boolean[n];

        makePermutation(0);

        System.out.print(sb.toString());
    }

    public static void makePermutation(int idx) {
        if(idx == n) {
            for(int i = 0; i < n; i++) {
                sb.append(numbers[i]);
                if(i != n - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
            return;
        }

        for(int i = 0; i < n; i++) {
            if(isSelected[i]) {
                continue;
            }
            numbers[idx] = i + 1;
            isSelected[i] = true;
            makePermutation(idx + 1);
            isSelected[i] = false;
        }
    }
}
