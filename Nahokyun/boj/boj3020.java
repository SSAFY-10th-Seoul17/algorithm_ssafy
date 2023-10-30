package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj3020 {

    private static int n;
    private static int[] down;
    private static int[] up;
    private static int h;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        down = new int[n /2];
        up = new int[n /2];
        for(int i = 0; i< n /2; i++) {
            down[i]=Integer.parseInt(br.readLine());
            up[i]=Integer.parseInt(br.readLine());
        }

        Arrays.sort(down);
        Arrays.sort(up);

        int min=n;
        int count=0;
        for(int i = 1; i<= h; i++){
            int boom=bs(i,down)+bs(h-i+1,up);
            if(min==boom){
                count++;
                continue;
            }
            if(min>boom){
                count=1;
                min=boom;
            }
        }


        System.out.println(min+" "+count);

    }

    private static int bs(int height,int[] arr){
        int left=0;
        int right=n/2;
        int mid=0;

        while(left<right){
            mid=(left+right)>>1;

            if(arr[mid]<height){
                left=mid+1;
            }else{
                right=mid;
            }
        }

        return n/2-left;
    }
}
