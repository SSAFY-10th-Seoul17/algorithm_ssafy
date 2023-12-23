import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [boj] 23284. 모든 스택 수열
 */
public class boj23284 {
    static int n;
    static int[] sequence;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        sequence = new int[n];

        solve(0, 0);

        System.out.print(sb.toString());
    }

    public static void solve(int depth, int nextNum) {
        if(depth == n) {
            for(int i = 0; i < n; i++) {
                sb.append(sequence[i]).append(" ");
            }
            sb.append("\n");

            return;
        }

        for(int i = 1; i <= n; i++) { // 현재 숫자
            boolean isDuplicateNum = false;

            for(int j = 0; j < depth; j++) {
                if(sequence[j] == i) {
                    isDuplicateNum = true;
                    break;
                }
            }

            if(isDuplicateNum) {
                continue;
            }

            sequence[depth] = i;

            if(depth >= 1 && sequence[depth - 1] < sequence[depth] && sequence[depth] < nextNum) { // 이 경우 문제 조건을 만족하지 않는 수열
                break;
            }

            if(sequence[depth] >= nextNum) { // 현재 들어오는 수가 새로운 수
                solve(depth + 1, sequence[depth] + 1);
            } else { // 현재 들어오는 수가 이미 들어온 수
                solve(depth + 1, nextNum);
            }
        }
    }
}
