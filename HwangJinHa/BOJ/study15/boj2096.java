import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] dpMax = {0, 0, 0};
    static int[] dpMaxNext = {0, 0, 0};

    static int[] dpMin = {0, 0, 0};
    static int[] dpMinNext = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
    static final int[] MAX_ARR = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};

    static int[] arr = {0, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++){
            dpMax[i] = Integer.parseInt(st.nextToken());
            dpMin[i] = dpMax[i];
        }

        for (int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            arr[0] = Integer.parseInt(st.nextToken());
            arr[1] = Integer.parseInt(st.nextToken());
            arr[2] = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 3; j++){
                for (int k = -1; k <= 1; k++){
                    int num = j + k;
                    if (0 <= num && num < 3){
                        dpMaxNext[j] = Math.max(dpMax[num] + arr[j], dpMaxNext[j]);
                    }
                    if (0 <= num && num < 3){
                        dpMinNext[j] = Math.min(dpMin[num] + arr[j], dpMinNext[j]);
                    }
                }
            }
            dpMax = dpMaxNext;
            dpMaxNext = new int[] {0, 0, 0};
            dpMin = dpMinNext;
            dpMinNext = Arrays.copyOf(MAX_ARR, 3);
        }
        System.out.print(Math.max(Math.max(dpMax[0], dpMax[1]), dpMax[2]));
        System.out.print(" " + Math.min(Math.min(dpMin[0], dpMin[1]), dpMin[2]));
    }
}
