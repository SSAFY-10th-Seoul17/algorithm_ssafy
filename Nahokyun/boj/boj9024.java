package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj9024{
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        for (int t = 0; t < test; t++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());
            int k=Integer.parseInt(st.nextToken());

            st=new StringTokenizer(br.readLine());
            int[] arr=new int[n];

            for(int i=0;i<n;i++) {
                arr[i]=Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);



            int diff=Integer.MAX_VALUE;
            int count=0;
            for(int i=0;i<n-1;i++) {
                int left=i+1;
                int right=n-1;
                int mid=0;
                int curSum=0;
                while(left+1<right) {
                    mid=(left+right)>>1;
                    curSum=arr[i]+arr[mid];

                    if(curSum<k) {
                        left=mid;
                    }else {
                        right=mid;
                    }

                }//근사값 차이나는 곳 탐색 완료

                int cmp=Math.abs(k-(arr[i]+arr[right]));
                int cmp2=Math.abs(k-(arr[i]+arr[left]));
                cmp=Math.min(cmp, cmp2);

                if(cmp<diff) {
                    diff=cmp;
                    count=1;
                }else if(cmp==diff) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
