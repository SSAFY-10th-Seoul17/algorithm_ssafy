import java.io.*;
import java.util.*;
public class boj16401 {
    static int m;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        binarySearch(arr, 1, arr[n - 1]);
    }

    public static void binarySearch(int[] arr, int start, int end){
        int result = 0;
        while(start <= end){
            int count = 0;
            int mid = (start + end) / 2;
            for(int i = 0; i < arr.length; i++){
                count += arr[i] / mid;
            }
            if(count >= m){
                result = mid;
                start = mid + 1;
            } else{
                end = mid - 1;
            }
        }
        System.out.println(result);
    }
}
