package toyproject.somedaybucket.myAlgo;

import java.io.*;
import java.util.*;

public class boj7795 {

    static int min = -1;
    private static int[] arr1;
    private static int[] arr2;

    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            arr1 = new int[Integer.parseInt(st.nextToken())];
            arr2 = new int[Integer.parseInt(st.nextToken())];

            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < arr1.length; i++){
                arr1[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < arr2.length; i++){
                arr2[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr1);
            Arrays.sort(arr2);

            for(int i = 0; i<arr1.length; i++){
                binarySearch(arr1[i], 0, arr2.length-1);
                ans += min+1;
                min = -1;
            }
            System.out.println(ans);
            ans = 0;
        }
    }

    private static void binarySearch(int target, int start, int end){
        if(start > end){
            return;
        }
        int mid = (end+start)/2;

        if(arr2[mid] < target) {
            if (min < mid) {
                min = mid;
            }
            binarySearch(target, mid+1, end);

        } else {
            binarySearch(target, start, mid-1);
        }
    }
}
