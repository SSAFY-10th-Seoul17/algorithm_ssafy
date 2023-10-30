package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj3079 {
    private static long[] arr;
    private static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        long m=Integer.parseInt(st.nextToken());
        arr = new long[n];

        long max=0;

        for(int i=0;i<n;i++) {
            arr[i]=Integer.parseInt(br.readLine());
            if(arr[i]>max)
                max=arr[i];
        }


        long left=0;
        long right=max*m;
        long mid;
        while(left+1<right) {
            mid=(left+right)>>1;
            if(check(mid,m)) {
                right=mid;
            }else {
                left=mid;
            }
        }
        System.out.println(right);

    }
    private static boolean check(long mid,long m) {
        long count=0;

        for(int i=0;i<n;i++) {
            count+=mid/arr[i];
            if(count>=m) {
                return true;
            }
        }
        return false;
    }
}
