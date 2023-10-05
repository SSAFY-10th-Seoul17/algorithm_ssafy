import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1300 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        System.out.println(binarySearch(n, k));
    }

    private static int binarySearch(int n, int k) {
        int left = 1;
        int right = k;

        while (left <= right) {
            int mid = (left + right) >> 1;

            if (getSequence(n, mid) < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private static int getSequence(int n, int value) {
        int sequence = 0;

        for (int i = n; i > 0; i--) {
            int div = value / i;
            if (n < div) {
                sequence += n * i;
                break;
            } else {
                sequence += div;
            }
        }

        return sequence;
    }

}

