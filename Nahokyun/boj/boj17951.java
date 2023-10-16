package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj17951 {

    private static int k;
    private static int n;
    private static int[] score;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());
        score = new int[n];
        int right=0;
        int left=0;

        for(int i=0;i<n;i++) {
            score[i]=Integer.parseInt(st.nextToken());
            right+=score[i];
        }

        int mid=0;

        while(left<=right) {
            mid=(left+right)>>1;

            if(check(mid)) {
                left=mid+1;
            }else {
                right=mid-1;
            }
        }

        System.out.println(right);

    }

    private static boolean check(int mid) {
        int sum=0;
        ArrayList<Integer> tmp=new ArrayList<>();
        for(int i=0;i<n;i++) {
            sum+=score[i];
            if(sum>=mid) {
                tmp.add(sum);
                sum=0;
            }
        }
        if(tmp.size()>=k) {//그룹이 많을때 -> 합을 늘려야함
            return true;
        }


        return false;
    }

}
