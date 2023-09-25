import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj17255 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        while (n > 0) {
            list.add(n % 10);
            n /= 10;
        }

        System.out.println(countSequence(list, 0, list.size() - 1));
    }

    private static int countSequence(List<Integer> list, int left, int right) {
        if (isRepeatedNumber(list, left, right)) {
            return 1;
        }

        return countSequence(list, left + 1, right) + countSequence(list, left, right - 1);
    }

    private static boolean isRepeatedNumber(List<Integer> list, int left, int right) {
        if (left == right) {
            return true;
        }

        for (int i = left + 1; i <= right; i++) {
            if (!Objects.equals(list.get(i - 1), list.get(i))) {
                return false;
            }
        }

        return true;
    }


}


