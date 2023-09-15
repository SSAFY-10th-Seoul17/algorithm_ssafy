import java.util.*;
import java.io.*;

public class boj2666 {
    static int n, l;
    static int first, second;
    static int min = Integer.MAX_VALUE;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        first = Integer.parseInt(st.nextToken()) - 1;
        second = Integer.parseInt(st.nextToken()) - 1;


        l = Integer.parseInt(br.readLine());

        nums = new int[l];
        for (int i = 0; i < l; i++) {
            nums[i] = Integer.parseInt(br.readLine()) - 1;
        }

        dfs(0, 0, first, second);

        System.out.println(min);
    }

    public static void dfs(int depth, int cnt, int first, int second) {
        if (depth == l) {
            min = Math.min(min, cnt);
            return;
        }
        int next = nums[depth];
        dfs(depth + 1, cnt + Math.abs(first - next), next, second);
        dfs(depth + 1, cnt + Math.abs(second - next), first, next);

    }

}
