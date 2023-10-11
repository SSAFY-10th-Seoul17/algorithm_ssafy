package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj15817 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        Pipe[] pipes = new Pipe[n+1];
        int[][] dp = new int[n+1][x + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pipes[i] = new Pipe(l, c);

        }
        // 입력 종료


        dp[0][0]=1;
        for (int i = 1; i <= n; i++) {
            int curLength=pipes[i].length;
            for (int j = 0; j <= x; j++) {
                if(j==0) {
                    dp[i][j]=1;
                    continue;
                }
                if(j<curLength) {
                    dp[i][j]=dp[i-1][j];
                    continue;
                }
                for (int count = 0; count <= pipes[i].quantity; count++) {
                    if (j >= count * curLength) {
                        dp[i][j]+=dp[i-1][j-curLength*count];
                    }
                }
            }
        }



        System.out.println(dp[n][x]);
    }

    static class Pipe {
        int length;
        int quantity;

        public Pipe(int length, int quantity) {
            super();
            this.length = length;
            this.quantity = quantity;
        }

    }

}
