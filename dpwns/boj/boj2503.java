import java.util.*;
import java.io.*;

class boj2503 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] input = new int[n][3];
        for(int i=0; i<n; i++) {
            input[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int result = 0;
        for(int i=123; i<=987; i++) {
            int[] num1 = divideNum(i);
            // 11 or 0 check
            if(Arrays.stream(num1).distinct().toArray().length != 3 || Arrays.stream(num1).anyMatch(a -> a==0)) continue;
            int j=0;
            for(; j<n; j++) {
                int st = 0, ball = 0;
                int[] num2 = divideNum(input[j][0]);
                // strike check
                for (int k = 0; k < 3; k++) {
                    if (num1[k] == num2[k]) st++;
                }
                if(st != input[j][1]) break;

                // ball check
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if (k == l) continue;
                        if (num1[k] == num2[l]) {
                            ball++;
                        }
                    }
                }
                if (ball != input[j][2]) break;

                if(input[j][1] == 0 && input[j][2] == 0) {  // 둘 다 0인 경우 일치하는 숫자가 없어야함
                    if(ball != 0 || st != 0) break;
                }
            }
            result = j == n ? result + 1 : result;
        }
        System.out.println(result);
        br.close();
    }
    public static int[] divideNum(int num) {
        int[] arr = new int[3];
        for(int i=2; i>=0; i--) {
            arr[i] = num % 10;
            num /= 10;
        }
        return arr;
    }
}