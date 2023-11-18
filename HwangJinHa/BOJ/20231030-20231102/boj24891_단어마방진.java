package study10월5주차목;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class boj24891_단어마방진 {
    static int l, n;
    static String[] strs;
    static char[][] chrs;
    static char[][] ans;
    static Set<Integer> used = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        strs = new String[n];
        chrs = new char[n][];
        ans = new char[l][];

        for (int i = 0; i < n; i++) {
            strs[i] = br.readLine();
        }

        Arrays.sort(strs);
        for (int i = 0; i < n; i++) {
            chrs[i] = strs[i].toCharArray();
        }

        dfs(0);
        System.out.println("NONE");
    }

    private static void dfs(int depth) {
        if (depth == l) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < l; j++) {
                    sb.append(ans[i][j]);
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }

        for (int i = 0; i < n; i++) {
            if (used.contains(i))
                continue;
            ans[depth] = chrs[i];
            if (check(depth)) {
                used.add(i);
                dfs(depth + 1);
                used.remove(i);
            }
        }
    }

    private static boolean check(int depth) {
        for (int i = 0; i < depth; i++) {
            if (ans[depth][i] != ans[i][depth])
                return false;
        }
        return true;
    }
}