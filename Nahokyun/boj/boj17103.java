package boj;

import java.util.Scanner;

public class boj17103 {
    public static boolean[] dp;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int test=sc.nextInt();
        dp=new boolean[1000001];

        for(int i=2;i*i<=1000000;i++){
            if(!dp[i]){
                //2 4 6 8 10 12 14 16 18 20    1 2 3 4 5 6 7 8 9
                //3 6 9 12 15 18 21 27         1 3 5 7 9 11
                //5 25 35 55                   1 5 7 11 13
                //7 49 77                      1 7 11 13
                //11 121 143                   1 11 13
                for(int j=i*i;j<=1000000;j+=i){
                    dp[j]=true;
                }
            }
        }

        for(int t=0;t<test;t++){
            int input=sc.nextInt();
            int answer=0;
            for(int i=2;i<=input/2;i++){
                if(!dp[i]&&!dp[input-i]){
                    answer++;
                }
            }
            System.out.println(answer);
        }
    }
}
