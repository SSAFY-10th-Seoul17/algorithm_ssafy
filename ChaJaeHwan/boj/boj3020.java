import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj3020 {

    static int N;
    static int H;
    static int[] up, down;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        up =  new int[H];
        down = new int[H];

        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(br.readLine())-1;
            if(i % 2 == 0) {
                down[h] += 1;
            } else {
                up[h] += 1;
            }
        }
        for(int i = H-2; i >= 0; i--) {
            down[i] += down[i+1];
            up[i] += up[i+1];
        }
        int min = Integer.MAX_VALUE;
        int minCnt = 0;
        for(int i = 0; i < H; i++) {
            int sum = down[i] + up[H-i-1];
            if(min > sum) {
                min = sum;
                minCnt = 1;
            } else if(min == sum) {
                minCnt += 1;
            }
        }
        System.out.println(min + " " + minCnt);
    }
}
