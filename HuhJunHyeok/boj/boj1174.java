import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * [boj] 1174. 줄어드는 수
 */
public class boj1174 {
    /**
     * N: 줄어드는 수의 작은 순서.
     */
    static int N;
    /**
     * digit: 사용할 숫자 배열. 줄어드는 수를 위해서 내림차순 정렬.
     */
    static int[] digit = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    /**
     * num: 만들어진 줄어드는 수의 리스트. 사용하는 경우 0번째는 사용하지 않음.(N번째와 index값 맞추기 위함)
     */
    static ArrayList<Long> num = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // digit 배열의 원소 9~0까지 총 10개의 숫자를 쓰냐 안 쓰냐 총 2^10 = 1024개의 경우의 수.
        // 모두 안 쓰는 경우와 0만 쓰는 경우는 동일한 경우. => 결과적으로는 1023개의 경우의 수.
        if(N > 1023) {
            System.out.println(-1);
        } else {
            num.add(-1L); // 0번째 index는 사용하지 않기 위해 들어갈 수 없는 값인 음수.

            dfs(0, 0);
            Collections.sort(num); // 줄어드는 수를 오름차순으로 정렬.

            System.out.println(num.get(N));
        }
    }

    public static void dfs(long descNum, int depth) {
        // 만들어지지 않았던 줄어드는 수이면 리스트에 저장.
        if(!num.contains(descNum)) {
            num.add(descNum);
        }

        // num 배열 모두 탐색 완료.
        if(depth == 10) {
            return;
        }

        // digit[depth]의 숫자 사용
        dfs(descNum * 10 + digit[depth], depth + 1);
        // digit[depth]의 숫자 미사용.
        dfs(descNum, depth + 1);
    }
}
