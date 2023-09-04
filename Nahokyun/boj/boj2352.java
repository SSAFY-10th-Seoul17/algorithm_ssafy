package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2352 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int n=Integer.parseInt(br.readLine());
        StringTokenizer st=new StringTokenizer(br.readLine());

        int[] arr=new int[n];

        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        int[] lis=new int[n];
        int idx=1;
        lis[0]=arr[0];
        for(int i=1;i<n;i++){
            if(arr[i]>lis[idx-1]){
                lis[idx++]=arr[i];
            }else{
                int left=0;
                int right=idx;
                int mid=0;
                while(left<right){
                    mid=(left+right)>>1;
                    if(lis[mid]>=arr[i]){
                        right=mid;
                    }else if(lis[mid]<arr[i]){
                        left=mid+1;
                    }
                }
                lis[right]=arr[i];
            }
        }

        System.out.println(idx);;




    }
}
