import java.io.*;
import java.util.*;

public class boj2467 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = arr.length - 1;
        int sum = 0;

        int resultLeft = 0;
        int resultRight = 0;
        int minValue = Integer.MAX_VALUE;

        while(left < right){
            sum = arr[left] + arr[right];
            if(Math.abs(minValue) > Math.abs(sum)){
                minValue = sum;
                resultLeft = left;
                resultRight = right;
            }
            if(sum == 0){
                break;
            }else if(sum < 0){
                left++;
            }else{
                right--;
            }
        }
        System.out.println(arr[resultLeft] + " " + arr[resultRight]);
    }
}
