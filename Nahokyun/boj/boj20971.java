package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj20971 {

    private static int[] arr;
    private static int s;
    private static int n;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int test=Integer.parseInt(br.readLine());

        for(int t=0;t<test;t++) {
            StringTokenizer st=new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());

            st=new StringTokenizer(br.readLine());
            arr = new int[n];

            int max=0;
            for(int i=0;i<n;i++) {
                arr[i]=Integer.parseInt(st.nextToken());
                max=Math.max(arr[i], max);
            }
            //입력종료

            Arrays.sort(arr);

            int left=0;
            int right=max;
            int mid=0;
            while(left+1<right) {
                mid=(left+right)>>1;

                if(check(mid)) {//좌석이 s개 보다 많이나옴 -> 간격 늘려야됨
                    left=mid;
                }else {
                    right=mid;
                }
            }
            System.out.println(left);

        }//테케 종료


    }//end of main

    private static boolean check(int mid) {

        int start=0;
        int count=1;

        for(int i=1;i<arr.length;i++) {
            if(arr[i]-arr[start]<mid) {
                continue;
            }
            start=i;
            count++;
        }

        return count>=s?true:false;

    }

}
