package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj18427 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n + 1][h + 1];
        ArrayList<Integer>[] al=new ArrayList[n+1];


        for (int i = 1; i <= n; i++) {
            al[i]=new ArrayList<>();
            st=new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()) {
                al[i].add(Integer.parseInt(st.nextToken()));
            }


            al[i].add(0);
        }
        // 입력 종료
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            dp[i][0]=1;
            for (int k = 1; k <= h; k++) {
                int tmp = 0;
                for (int j = 0; j < al[i].size(); j++) {
                    int cur = al[i].get(j);

                    if(cur<=k){
                        tmp+=dp[i-1][k-cur]%10007;
                    }

                }
                dp[i][k] = tmp%10007;
            }
        }

        System.out.println(dp[n][h]);

    }

}
