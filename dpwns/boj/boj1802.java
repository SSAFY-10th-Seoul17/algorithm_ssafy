import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static char[] input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            // 동호의 방법은 절반씩 줄여나가는 방법
            input = br.readLine().toCharArray();
            if(check(-1, input.length)) {
                sb.append("YES");
            }
            else sb.append("NO");
            sb.append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    public static boolean check(int left, int right) {
        if(left+1 == right) return true;

        int mid = (left + right) / 2; // 접는 부분
        while(++left < mid && --right > mid) {
            if(input[left] == input[right]) return false;   // 마주보는 부분이 같은 방향으로 접힐 수 없음
        }
        return check(-1, mid);
    }
}
