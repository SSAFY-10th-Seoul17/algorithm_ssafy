import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        lastValue = arr[0] * arr[n - 1];
        
        dfs(arr, n, 0);
        System.out.println(result);
    }

    static int lastValue, result;

    private static void dfs(int[] arr, int size, int sum) {
        if (size <= 3) {
            result = Math.max(result, lastValue + sum);
            return;
        }

        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] < 0) {
                continue;
            }
            
            int value = getValue(arr, i);
            arr[i] *= -1;
            dfs(arr, size - 1, sum + value);
            arr[i] *= -1;
        }
    }

    private static int getValue(int[] arr, int idx) {
        int a = 0;
        int b = 0;
        for (int i = idx + 1; i < arr.length; i++) {
            if (arr[i] > 0) {
                a = arr[i];
                break;
            }
        }

        for (int i = idx - 1; i >= 0; i--) {
            if (arr[i] > 0) {
                b = arr[i];
                break;
            }
        }

        return a * b;
    }

}
