import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n;
    static int[] nums;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        nums = new int[n];
        nums[0] = 1;
        makeGoodNums(1);
    }

    public static boolean check(int idx) {
        for (int i = 1; i <= idx/2; i++) {
            boolean flag = false;
            for (int j = 0; j < i; j++) {
                if (nums[idx-i-j-1] != nums[idx-j-1]) {
                    flag = true;
                }
            }
            if (!flag) return false;
        }
        return true;
    }

    public static void makeGoodNums(int idx) {
        if (flag) {
            return;
        }

        if (idx == n) {
            for (int i = 0; i < n; i++) {
                System.out.print(nums[i]);
            }
            flag = true;
            return;
        }

        for (int i = 1; i < 4; i++) {
            nums[idx] = i;
            if (check(idx + 1)) {
                makeGoodNums(idx+1);
            }
        }
    }
}
