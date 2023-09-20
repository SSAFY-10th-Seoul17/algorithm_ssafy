package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int n=Integer.parseInt(br.readLine());
        int[][] maxDp=new int[2][4];
        int[][] minDp=new int[2][4];
        int[] tmp=new int[4];
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=1;i<=3;i++){
            int cur=Integer.parseInt(st.nextToken());
            maxDp[0][i]=cur;
            minDp[0][i]=cur;
        }



        for(int i=1;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=1;j<=3;j++){
                tmp[j]=Integer.parseInt(st.nextToken());
                if(j==1){
                    maxDp[1][1]=Math.max(maxDp[0][1]+tmp[1],maxDp[0][2]+tmp[1]);
                    minDp[1][1]=Math.min(minDp[0][1]+tmp[1],minDp[0][2]+tmp[1]);
                }
                else if(j==2){
                    maxDp[1][2]=Math.max(maxDp[0][1],Math.max(maxDp[0][2],maxDp[0][3]))+tmp[2];
                    minDp[1][2]=Math.min(minDp[0][1],Math.min(minDp[0][2],minDp[0][3]))+tmp[2];
                }else if(j==3){
                    maxDp[1][3]=Math.max(maxDp[0][2],maxDp[0][3])+tmp[3];
                    minDp[1][3]=Math.min(minDp[0][2],minDp[0][3])+tmp[3];
                }

            }

            for(int j=1;j<=3;j++){
                maxDp[0][j]=maxDp[1][j];
                minDp[0][j]=minDp[1][j];
            }
        }

        System.out.println(Math.max(Math.max(maxDp[0][1],maxDp[0][2]),maxDp[0][3])+" "+Math.min(Math.min(minDp[0][1],minDp[0][2]),minDp[0][3]));


    }
}
