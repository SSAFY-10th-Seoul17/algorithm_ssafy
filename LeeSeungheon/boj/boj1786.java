import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class boj1786 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[] text = br.readLine().toCharArray();
        char[] pattern = br.readLine().toCharArray();
        int[] pi = getPi(pattern);

        List<Integer> solve = solve(text, pattern, pi);
        sb.append(solve.size()).append("\n");

        for (Integer integer : solve) {
            sb.append(integer).append(" ");
        }
        System.out.println(sb);
    }

    private static int[] getPi(char[] pattern) {
        int[] pi = new int[pattern.length];

        for (int piLen = 1, dupleLen = 0; piLen < pattern.length; piLen++) {

            while (dupleLen > 0 && pattern[piLen] != pattern[dupleLen]) {
                dupleLen = pi[dupleLen - 1];
            }

            if (pattern[piLen] == pattern[dupleLen]) {
                pi[piLen] = ++dupleLen;
            } else {
                pi[piLen] = 0;
            }
        }
        return pi;
    }

    private static List<Integer> solve(char[] text, char[] pattern, int[] pi) {

        List<Integer> list = new ArrayList<>();
        int max = text.length;
        for (int idx = 0, dupleLen = 0; idx < max; idx++) {

            while (dupleLen > 0 && text[idx] != pattern[dupleLen]) {
                dupleLen = pi[dupleLen - 1];
            }

            if (text[idx] == pattern[dupleLen]) {
                if (dupleLen == pattern.length - 1) {
                    list.add(idx - dupleLen + 1);
                    dupleLen = pi[dupleLen];
                }else {
                    dupleLen ++;
                }
            }
        }
        return list;
    }
}
