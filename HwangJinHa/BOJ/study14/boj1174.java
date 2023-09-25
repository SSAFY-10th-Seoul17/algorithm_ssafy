import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class boj1174 {
    static int n;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.valueOf(br.readLine());

        System.out.println(bfs());
    }

    private static long bfs() {
        Queue<Long> q = new LinkedList<>();
        if (n == 1)
            return 0;
        cnt++;
        for (long i = 1; i < 10; i++){
            q.add(i);
        }

        long num = 0;
        while (!q.isEmpty()){
            num = q.poll();
            cnt++;
            if (n == cnt)
                return num;
            num *= 10;
            for (int i = 0; i < 10; i++){
                if (check(num + i)){
                    q.add(num + i);
                } else{
                    break;
                }
            }
        }
        return -1;
    }

    static boolean check(long num){
        int cnt = 0;
        while (num > 9) {
            long num0 = num % 10;
            num /= 10;
            long num1 = num % 10;
            if (num1 <= num0){
                return false;
            }
        }
        return true;
    }
}
