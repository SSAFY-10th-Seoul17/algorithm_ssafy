import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[] obst1 = new int[h+1];
        int[] obst2 = new int[h+1];

        for (int i = 0; i < n; i++) {
            int idx = Integer.parseInt(br.readLine());
            if (i % 2 == 0) {
                obst1[idx] += 1;
            } else {
                obst2[idx] += 1;
            }

        }

        for (int i = obst1.length-2; i > 0 ; i--) {
            obst1[i] += obst1[i+1];
            obst2[i] += obst2[i+1];
        }

        int[] result = new int[h+1];
        for (int i = 1; i < obst1.length; i++) {
            result[i] = obst1[i] + obst2[obst1.length-i];
        }


        int minCnt = Integer.MAX_VALUE;
        int sec = Integer.MAX_VALUE;

        for (int i = 1; i < result.length; i++) {
            if (result[i] < minCnt) {
                minCnt = result[i];
                sec = 1;
            }
            else if (result[i] == minCnt) {
                sec++;
            }
        }

        System.out.println(minCnt + " " + sec);


    }
}
