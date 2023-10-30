package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1300 {

    private static long n;
    private static long k;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());


        long left=0;
        long right=n*n;
        long mid=0;

        while(left+1<right) {
            mid=(left+right)>>1;

            if(check(mid)) {
                left=mid;
            }else {
                right=mid;
            }
        }
        System.out.println(right);

    }

    private static boolean check(long mid) {
        long count=0;

        for(long i=1;i<=n;i++) {
            count+=mid/i>=n?n:mid/i;

            if(count>=k) {//수가 많으면 false 리턴 =>수를 줄여야함
                return false;
            }
        }


        return count>=k?false:true;
    }

}
