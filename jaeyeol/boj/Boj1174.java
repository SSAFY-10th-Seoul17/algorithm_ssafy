import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1174 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(getDecreaseNumber(n));
    }
    private static long getDecreaseNumber(int n) {
        int[] numbers = new int[11];
        Arrays.fill(numbers, 10);
        numbers[0] = 0;
        int top = 0;

        while (--n > 0) {
            if (++numbers[0] >= numbers[1]) {
                top = checkNumber(numbers, top);
                if (top == 10) {
                    return -1;
                }
            }
        }

        return arrayToNumber(numbers, top);
    }

    private static long arrayToNumber(int[] numbers, int top) {
        long result = 0;
        long ten = 1;

        for (int i = 0; i <= top; i++) {
            result += numbers[i] * ten;
            ten *= 10;
        }
        return result;
    }

    private static int checkNumber(int[] numbers, int top) {
        for (int i = 0; i < top; i++) {
            if (++numbers[i] < numbers[i + 1]) {
                return top;
            } else {
                numbers[i] = i;
            }
        }

        if (++numbers[top] >= 10) {
            top++;
            for (int i = 0; i <= top; i++) {
                numbers[i] = i;
            }
        }

        return top;
    }

}


