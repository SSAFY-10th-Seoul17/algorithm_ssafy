import java.io.*;
import java.util.*;

public class boj23829 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        long[] prefix = new long[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            int p = Integer.parseInt(br.readLine());
            int idx = binarySearch(arr, p);

            sb.append((prefix[n] - prefix[idx] - ((long) p * (n - idx))) + (Math.abs(prefix[idx] - ((long) p * idx)))).append("\n");
        }
        System.out.println(sb);
    }

    static int binarySearch(int[] arr, int p){
        int left = 0;
        int right = arr.length;

        while(left < right){
            int mid = (left + right) / 2;

            if(arr[mid] < p){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return right;
    }
}