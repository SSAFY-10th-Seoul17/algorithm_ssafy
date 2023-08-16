package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj2624 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());//만들어야 하는 액수
        int k = Integer.parseInt(br.readLine());//동전 종류

        int[][] dp = new int[k + 1][t + 1];//dp배열
        ArrayList<Coin> coins = new ArrayList<>();

        for (int i = 1; i <= k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int p = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            coins.add(new Coin(p, n));
        }//입력 종료

        dp[0][0] = 1;
        for (int i = 1; i <= k; i++) {
            int curVal = coins.get(i - 1).value;
            int curCount = coins.get(i - 1).count;
            for (int j = 0; j <= t; j++) {
                if (j == 0) {//초기화
                    dp[i][j] = 1;
                    continue;
                }
                if (j < curVal) {//현재 사용될 값보다 작을경우 이전 dp값 그대로 사용
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }

                for (int c = 0; c <= curCount; c++) {
                    if (j - c * curVal >= 0) {
                        dp[i][j] += dp[i - 1][j - c * curVal];
                    }
                }


            }
        }

        System.out.println(dp[k][t]);
    }//main 종료
}

class Coin {
    int value;
    int count;

    public Coin(int value, int count) {
        this.value = value;
        this.count = count;
    }
}
