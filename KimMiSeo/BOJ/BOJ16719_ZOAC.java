import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 대승헌님의 멋진 설명을 듣고 해결할 수 있었습니다! 짱 👍
 * 처음에는 반복문을 사용해서 해결하려고 했으나, 생각처럼 잘 진행되지 않아 질문을 했고, 재귀로 짜보라는 조언을 얻어 재귀로 해결하였습니다.
 */

public class BOJ16719_ZOAC {
    static String input;
    static boolean[] visited;
    static int N;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        input = br.readLine();
        N = input.length();
        visited = new boolean[N];

        // 첫글자 고르기
        int idx = -1;
        char temp = 'a';
        for (int i = 0; i < N; i++) {
            if (input.charAt(i) - '0' < temp - '0') {
                temp = input.charAt(i);
                idx = i;
            }
        }

        sb.append(input.charAt(idx)).append("\n"); // 첫번째
        visited[idx] = true;
        solve(0, idx, N - 1);
        System.out.println(sb.toString());
    }

    // 탐색하기
    private static void solve(int start, int index, int end) {
        // 오른쪽
        if (index == end && start == index) {
            return;
        }

        if (index != end) {
            int idx = -1;
            char temp = 'a';

            for (int i = index + 1; i <= end; i++) {
                // 제일 작은 것 찾기
                if (input.charAt(i) - '0' < temp - '0') {
                    temp = input.charAt(i);
                    idx = i;
                }
            }

            if (idx != -1) { // 제일 작은 거 찾았으면
                visited[idx] = true;
                print();
                solve(index + 1, idx, end);
            }
        }

        // 왼쪽
        if (start != index) {
            int idx = -1;
            char temp = 'a';

            for (int i = start; i < index; i++) {
                // 제일 작은 것 찾기
                if (input.charAt(i) - '0' < temp - '0') {
                    temp = input.charAt(i);
                    idx = i;
                }
            }

            if (idx != -1) { // 제일 작은 거 찾았으면
                visited[idx] = true;
                print();
                solve(start, idx, index - 1);
            }
        }
    }

    private static void print() {
        String answer = "";

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                answer += input.charAt(i);
            }
        }
        sb.append(answer).append("\n");
    }

}
