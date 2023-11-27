import java.io.*;
import java.util.*;

public class boj3151 {

    static int[] arr;
    static int n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long result = 0;

        for (int i = 0; i < n; i++) {
            result += binarySearch(i);
        }


        System.out.println(result);

    }
    static int binarySearch(int pivot){

        int left = pivot + 1;
        int right = n - 1;

        int cnt = 0;

        while(left < right){

            int sum = arr[left] + arr[pivot] + arr[right];

            if(sum == 0){
                if(arr[left] == arr[right]){
                    cnt += right - left;
                    left++;
                }else{
                    int lcnt = 0, rcnt = 0;
                    int leftValue = arr[left];
                    int rightValue = arr[right];
                    while(leftValue == arr[left++]){
                        lcnt++;
                        if(leftValue != arr[left])break;
                    }
                    while(rightValue == arr[right--]){
                        rcnt++;
                        if(rightValue != arr[right]) break;
                    }
                    cnt += lcnt * rcnt;
                }

            }else if(sum < 0){
                left++;
            }else{
                right--;
            }
        }
        return cnt;
    }
}