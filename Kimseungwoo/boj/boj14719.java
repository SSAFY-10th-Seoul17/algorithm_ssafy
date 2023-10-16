package toyproject.somedaybucket.myAlgo.boj;

import java.io.*;
import java.util.*;

public class boj14719 {

    private static int N, M;
    private static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        a = new int[M];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < M; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        int rain = 0;
        int start, last;
        int i = 0;
        while(i+1 < M){
            if(a[i] <= a[i+1]){
                i++;
            } else {
                start = i;
                last = start+1;
                for(int j = start+1; j < M; j++){
                    if(a[last] <= a[j]){
                        last = j;
                        if(a[last] >= a[start]){
                            break;
                        }
                    }
                }

                int standard = (a[last] > a[start]) ? a[start] : a[last];
//                System.out.println("start : " + start + " last : " + last + " standard : " + standard);
                for(int j = start+1; j < last; j++){
                    rain += (standard - a[j]);
                }

                i = last;
            }
        }


        System.out.println(rain);
    }
}
