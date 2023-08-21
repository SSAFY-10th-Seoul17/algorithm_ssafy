import java.io.*;
import java.util.*;
public class boj7795 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < t; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[] arrA = new int[n];
            int[] arrB = new int[m];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arrA[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                arrB[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arrA);
            Arrays.sort(arrB);
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++){
                    if(arrA[i] <= arrB[j]) break;
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }
}
