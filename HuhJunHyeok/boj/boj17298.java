import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * [boj] 17298. 오큰수
 */
public class boj17298 {
    /**
     * N: 수열의 크기
     */
    static int N;
    /**
     * sequence: 수열
     */
    static int[] sequence;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        sequence = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N; i++) {
            while(!stack.isEmpty() && sequence[i] > sequence[stack.peek()]) {
                sequence[stack.pop()] = sequence[i];
            }
            stack.push(i);
        }

        while(!stack.isEmpty()) {
            sequence[stack.pop()] = -1;
        }

        for(int value: sequence) {
            sb.append(value).append(" ");
        }

        System.out.println(sb.toString());
    }
}
