import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

/**
 * [boj] 17255. N으로 만들기
 */
public class boj17255 {
    /**
     * N: 만들려는 수. (0 <= N <= 10,000,000)
     */
    static String N;
    /**
     * len: N 배열의 길이
     */
    static int len;
    /**
     * pathSet: N을 만들었을 때 만드는 과정을 이어 붙인 경로의 HashSet
     */
    static HashSet<String> pathSet = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = br.readLine();
        len = N.length();

        for(int i = 0; i < len; i++) {
            String now = String.valueOf(N.charAt(i));
            dfs(now, now, i, i);
        }

        System.out.println(pathSet.size());
    }

    public static void dfs(String now, String path, int left, int right) {
        if(left == 0 && right == len - 1) {
            pathSet.add(path);
            return;
        }

        if(left - 1 >= 0) { // 왼쪽에 이어 붙일 digit이 존재
            String next = N.charAt(left - 1) + now;
            dfs(next, path + next, left - 1, right);
        }

        if(right + 1 < len) { // 오른쪽에 이어 붙일 digit이 존재
            String next = now + N.charAt(right + 1);
            dfs(next, path + next, left, right + 1);
        }
    }
}
