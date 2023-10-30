package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2792 {

    private static int[] arr;
    private static int m;
    private static int n;
    static int mod=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st=new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];

        int left=0;
        int right=0;
        int mid=0;
        for(int i = 0; i< m; i++){
            arr[i]=Integer.parseInt(br.readLine());
            right= Math.max(arr[i],right);
        }
        //입력종료


        while(left+1<right){
            mid=(left+right)>>1;

            if(check(mid)){
                left=mid;
            }else{
                right=mid;
            }

        }

//        for(int i=0;i<m;i++){
//            mod=Math.max(arr[i]%right,mod);
//        }

        System.out.println(right);


    }

    private static boolean check(int mid) {
        int count=0;
        for(int i=0;i<m;i++){
            count+=arr[i]/mid;
            if(arr[i]%mid!=0){
                count+=1;
            }
            //mod=Math.max(arr[i]%mid,mod);
        }

        return count > n;//인원수가 더 많을때 -> 인당 주는 보석수를 늘려야함
    }
}
