import java.io.*;
import java.util.*;

public class boj15817 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int[] pipe = new int[x + 1];
        pipe[0] = 1;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int length = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = x; j >= length; j--) {
                for (int k = 1; k <= cnt; k++) {
                    if(j - length * k >= 0){
                        pipe[j] += pipe[j - length * k];
                    }
                }
            }
        }

        System.out.println(pipe[x]);
    }
}