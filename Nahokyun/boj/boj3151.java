package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class boj3151 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        long result = 0;
        for (int i = 0; i < n - 1; i++) {

            int count = 1;

            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum > 0) {
                    right--;
                    continue;
                }
                if (sum < 0) {
                    left++;
                    continue;
                }

                //합이 0이면서 같은값이 두개일때
                if(arr[left]==arr[right]) {
                    count=right-left+1;
                    result+=count*(count-1)/2;
                    break;
                }
                //합이 0이면서 같지 않을때
                int lcount=1;
                while(left+lcount<right&&arr[left]==arr[left+lcount]) {
                    lcount++;
                }
                int rcount=1;
                while(arr[right]==arr[right-rcount]) {
                    rcount++;
                }


                result+=lcount*rcount;
                left+=lcount;
                right-=rcount;

            }
        }

        System.out.println(result);

    }

}
