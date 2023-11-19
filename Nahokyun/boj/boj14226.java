package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj14226 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s = Integer.parseInt(br.readLine());

        CountAndClip[] dp;

        if (s >= 4) {
            dp = new CountAndClip[s + 1];
        } else
            dp = new CountAndClip[7];

        dp[1] = new CountAndClip(0, 0);
        dp[2] = new CountAndClip(2, 1);
        dp[3] = new CountAndClip(3, 1);
        dp[4] = new CountAndClip(4, 2);


        for (int i = 5; i <= s; i++) {
            int maxClip = 0;
            int minOper = 10000;

            for (int j = 2; j < i; j++) {
                //클립보드 저장해서 배수해준뒤 빼기

                int c = 0;
                while (j + c * j < i) {
                    c++;
                }
                int curCount = dp[j].operCount + 1 + c + ((c + 1) * j - i);
                int curClip = j;


                //현재 클립보드 수 사용해준뒤 빼기
                int tmpClip = dp[j].clip;
                int count = 0;
                while (j + tmpClip * count < i) {
                    count++;
                }

                if (curCount > dp[j].operCount + count + j + count * tmpClip - i) {
                    curClip = tmpClip;
                    curCount = dp[j].operCount + count + j + count * tmpClip - i;
                }


                //최소비교
                if (minOper > curCount) {
                    minOper = curCount;
                    maxClip = curClip;
                }
            }


            dp[i] = new CountAndClip(minOper, maxClip);

        }

        System.out.println(dp[s].operCount);


    }

    static class CountAndClip {
        int operCount;
        int clip;

        public CountAndClip(int operCount, int clip) {
            super();
            this.operCount = operCount;
            this.clip = clip;
        }

    }
}
