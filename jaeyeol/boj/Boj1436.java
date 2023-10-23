import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1436 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(getTitle(n));
    }

    private static String getTitle(int n) {
        int left = 0;
        int right = -1;

        while (--n > 0) {
            left++;

            if (left % 10 == 6) {
                int leftNumber = left;
                int count = 1;
                int formatCount = 0;
                while (leftNumber % 10 == 6) {
                    leftNumber /= 10;
                    count *= 10;
                    formatCount++;
                }

                while (++right < count) {
                    if (--n <= 0) {
                        return (leftNumber != 0 ? leftNumber : "") + "666" + String.format("%0" + formatCount + "d", right);
                    }
                }
                right = -1;
                left++;
            }
        }

        return (left != 0 ? left : "") + "666";
    }


}

