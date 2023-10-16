package toyproject.somedaybucket.myAlgo.boj;

import java.io.*;
import java.util.*;

public class boj17951 {
    static int N;
    static int K;
    static int[] score;
    private static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        score = new int[N];
        st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < N; i++){
            score[i] = Integer.parseInt(st.nextToken());
        }

        divide(1, 20 * 100_000 + 1);

        System.out.println(ans);
    }

    public static void divide(int start, int end){
        if(start > end) return;

        int mid = start + (end - start) / 2;

        int sum = 0;
        int group = 0;
        for(int e : score){
            sum += e;
            if(sum >= mid){
                group++;
                sum = 0;
            }
        }

        if(group >= K){
            ans = mid;
            divide(mid+1, end);
        } else {
            divide(start, mid-1);
        }
    }

}
