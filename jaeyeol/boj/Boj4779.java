import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Boj4779 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String read;

        int max = 0;
        List<Integer> testCases = new ArrayList<>();
        while ((read = br.readLine()) != null) {
            int n = Integer.parseInt(read);
            n = (int) Math.pow(3, n);
            testCases.add(n);
            max = Math.max(max, n);
        }

        StringBuilder result = new StringBuilder();
        char[] chars = new char[max];
        for (int testCase : testCases) {
            Arrays.fill(chars, 0, testCase, '-');
            dfs(chars, 0, testCase - 1, testCase);

            for (int i = 0; i < testCase; i++) {
                result.append(chars[i]);
            }
            result.append("\n");
        }

        System.out.print(result);
    }

    private static void dfs(char[] chars, int left, int right, int length) {
        if (length <= 1) {
            return;
        }

        length /= 3;
        for (int i = 0; i < length; i++) {
            chars[left + length + i] = ' ';
        }

        dfs(chars, left, right - length, length);
        dfs(chars, left + length * 2, right, length);
    }

}

