import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()), cnt = 11;
        long val = 10;
        if (n <= 11) {
            System.out.println(n - 1);
        } else {
            while (n > cnt) {
                val = findNext(val);
                if (val > 9876543210L) break;
                cnt++;
            }
            System.out.println(val > 9876543210L ? -1 : val);
        }
        br.close();
    }
    public static long findNext(long num) {
        long res = 0;
        int idx = 0;
        int[] seq = new int[12];     // 자리 수 표현
        Arrays.fill(seq, -1);
        while(num > 0) {
            seq[idx++] = (int) (num % 10);
            num /= 10;
        }

        idx = 0;
        while(seq[idx+1] > -1) {    // 자기 앞이 -1이 아닐 때 까지
            if(seq[idx] + 1 >= seq[idx+1]) { // 321 -> 322가 됨 -> 다음 수? 410, 410 -> 411 ? -> 420
                seq[idx] = idx-1 == -1 ? 0 : seq[idx-1]+1;

                if(seq[idx+1] == 9) {   // 제일 앞의 값
                    seq[idx+2] = seq[idx+2] == -1 ? seq[idx] + 2: seq[idx+2] + 1;
                    seq[idx+1] = seq[idx] + 1;
                }
                else {
                    seq[idx+1]++;
                }
                if(seq[idx+2] != -1 && seq[idx+1] < seq[idx+2]) break;
            }
            else {
                seq[idx]++;
                break;
            }
            idx++;
        }

        for (int i = 10; i >= 0; i--) {
            if(seq[i] != -1) res = res*10 + seq[i];
        }
        return res;
    }
}
