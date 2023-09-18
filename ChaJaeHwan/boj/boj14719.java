import java.io.*;
import java.util.*;

public class boj14719 {

    static int H, W;
    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[W];
        for (int i = 0; i < W; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int max = -1;
        int maxIdx = -1;
        int sum = 0;
        int cnt = 0;
        for (int i = 0; i < W; i++) {
            if(max == -1) {
                max = arr[i];
                maxIdx = i;
            } else if(arr[i] >= max) {
               int total = 0;
               for(int j = i-1; j > maxIdx; j--) {
                   total += (max-arr[j]);
               }
               sum += total;
               maxIdx = i;
               max = arr[i];
               cnt = 0;
           } else {
                int tempMax = arr[i];
                for (int j = i + 1; j < W; j++) {
                    if(arr[j] > tempMax){
                        tempMax = arr[j];
                    }
                }
//                System.out.println("i = " + i +" tempMax = " + tempMax + " arr[i] = " + arr[i]);
                cnt += tempMax-arr[i];
            }
        }
//        System.out.println(cnt);
        sum += cnt;
        System.out.println(sum);

    }
}
