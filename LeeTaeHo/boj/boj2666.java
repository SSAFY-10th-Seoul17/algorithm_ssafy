import java.io.*;
import java.util.*;

public class boj2666 {

    static int result, n, m;
    static int[] targets;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int o1 = Integer.parseInt(st.nextToken());
        int o2 = Integer.parseInt(st.nextToken());

        int left = Math.min(o1, o2);
        int right = Math.max(o1, o2);

        m = Integer.parseInt(br.readLine());

        targets = new int[m];

        for (int i = 0; i < m; i++) {
            targets[i] = Integer.parseInt(br.readLine());
        }

        result = Integer.MAX_VALUE;

        dfs(left, right, 0, 0);

        System.out.println(result);
    }

    private static void dfs(int left, int right, int cnt ,int depth){
        if(depth == m){
            result = Math.min(result, cnt);
            return;
        }

        if(left >= targets[depth]){
            dfs(targets[depth], right, cnt + left - targets[depth], depth + 1);
        } else if (left < targets[depth] && targets[depth] < right) {
            dfs(targets[depth], right, cnt + Math.abs(left - targets[depth]), depth + 1);
            dfs(left, targets[depth], cnt + Math.abs(right - targets[depth]), depth + 1);
        }else{
            dfs(left, targets[depth], cnt + targets[depth] - right, depth + 1);
        }
    }
}
