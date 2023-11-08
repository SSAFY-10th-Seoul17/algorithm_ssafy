import java.io.*;
import java.util.*;

public class boj25795 {
    static final int MOD = 100000;
    static int n, b, c;
    static long max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        max = 0;

        dfs(a + b,1, 0, 1);
        System.out.println(max);
    }

    static void dfs(long result, int white, int dark, int depth){
        if(depth == n + n){
            if(result > max ) max = result;
            return;
        }

        if(white != n){
            dfs((result + b) % MOD, white + 1, dark, depth + 1);
        }
        if(white > dark && dark != n){
            dfs((result * c) % MOD, white, dark + 1, depth + 1);
        }
    }
}