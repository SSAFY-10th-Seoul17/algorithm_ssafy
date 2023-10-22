import java.io.*;
import java.util.*;

public class boj2792 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] jewelry = new int[m];

        int end = 0;

        for (int i = 0; i < m; i++) {
            jewelry[i] = Integer.parseInt(br.readLine());
            if(end < jewelry[i]){
                end = jewelry[i];
            }
        }

        int start = 1;
        int mid;
        int result = 0;


        while(start <= end){
            mid = (start + end) / 2;

            int sum = 0;

            for (int i : jewelry) {
                if(i % mid == 0){
                    sum += i / mid;
                }else{
                    sum += (i / mid) + 1;
                }


            }

            if(sum > n){
                start = mid + 1;
            }else {
                end = mid - 1;
                result = mid;
            }
        }
        System.out.println(result);

    }
}