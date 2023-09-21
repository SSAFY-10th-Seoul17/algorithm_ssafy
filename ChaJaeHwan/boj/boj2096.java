import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj2096 {

    static int N;

    static int[] max = new int[3];
    static int[] min = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 3; i++) {
            int n = Integer.parseInt(st.nextToken());
            max[i]= n;
            min[i] = n;
        }
        for(int i = 1; i< N; i++){
            int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j = 0; j < 3; j++){
                if(j == 0) {
                    max[j] += Math.max(temp[j], temp[j+1]);
                    min[j] += Math.min(temp[j], temp[j+1]);
                } else if(j == 1) {
                    max[j] += Math.max(temp[j-1], Math.max(temp[j], temp[j+1]));
                    min[j] += Math.min(temp[j-1], Math.min(temp[j], temp[j+1]));
                } else {
                    max[j] += Math.max(temp[j-1], temp[j]);
                    min[j] += Math.min(temp[j-1], temp[j]);
                }
            }
        }
        System.out.println(Arrays.stream(max).max().getAsInt() + " " + Arrays.stream(min).min().getAsInt());
    }
}
