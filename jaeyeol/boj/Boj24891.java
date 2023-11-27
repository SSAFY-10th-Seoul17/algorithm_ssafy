import java.io.*;
import java.util.*;

public class Boj24891 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int l = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }
        Arrays.sort(words);

        int[] sequence = new int[l];
        if (dfs(words, sequence, new boolean[n], 0)) {
            StringBuilder result = new StringBuilder();
            for (int s : sequence) {
                result.append(words[s]).append('\n');
            }
            System.out.println(result);
        } else {
            System.out.println("NONE");
        }
    }

    private static boolean dfs(String[] words, int[] sequence, boolean[] visited, int depth) {
        if (depth >= sequence.length) {
            return true;
        }

        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && isPossible(words, sequence, depth, i)) {
                visited[i] = true;
                sequence[depth] = i;
                if (dfs(words, sequence, visited, depth + 1)) {
                    return true;
                }
                visited[i] = false;
            }
        }

        return false;
    }

    private static boolean isPossible(String[] words, int[] sequence, int depth, int cur) {
        for (int i = 0; i < depth; i++) {
            if (words[cur].charAt(i) != words[sequence[i]].charAt(depth)) {
                return false;
            }
        }

        return true;
    }

}

