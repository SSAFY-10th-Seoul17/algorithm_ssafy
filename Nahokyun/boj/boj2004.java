package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2004 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n= Integer.parseInt(st.nextToken());
        int m= Integer.parseInt(st.nextToken());

        //nCm 구하기 실패
        //n!/(n-m)!*m!
        //2000 1999 1998

        int two=0;
        int five=0;
        if(n/2<m){//연산 감소 ex) 25C24->25C1
            m=n-m;
        }
        two=count(n,2)-count(n-m,2)-count(m,2);
        five=count(n,5)-count(n-m,5)-count(m,5);

        System.out.println(Math.min(two, five));
    }
    private static int count(int num,int base){
        int count=0;
        int t=1;
        while((int)(num/Math.pow(base,t))!=0){
            count+=num/Math.pow(base,t++);
        }
        return count;
    }
}
/*
        long[][] dp=new long[2_000][2_000];//nCm= n-1Cm+n-1Cm-1
        for(int i=0;i<=n;i++){
            dp[i][0]=1;
            dp[i][i]=1;
            dp[i][1]=i;
        }


        for(int i=2;i<=m;i++){
            for(int j=2;j<=n;j++){
                if(dp[j][i]==0)
                    dp[j][i]=dp[j-1][i]+dp[j-1][i-1];
            }
        }
        for(int i=0;i<=n;i++){
            for(int j=0;j<=m;j++){
                System.out.printf(dp[i][j]+" ");
            }
            System.out.println();
        }
        int count=0;
        while(dp[n][m]%10==0){
            count++;
            dp[n][m]=dp[n][m]/10;
        }
        System.out.println(count);
 */