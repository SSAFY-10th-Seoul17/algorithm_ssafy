import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * [boj] 16198. 에너지 모으기
 */
public class boj16198 {
    /**
     * N: 에너지 구슬의 수
     */
    static int N, sumEnergy;
    /**
     * weights: 에너지 구슬의 무게
     */
    static ArrayList<Integer> weights = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            weights.add(Integer.parseInt(st.nextToken()));
        }

        dfs(N, 0);

        System.out.println(sumEnergy);
    }

    /**
     * dfs를 통해 문제에서 답을 구하는 방법인
     * 에너지 구슬 하나를 고른다. 고른 에너지 구슬의 번호를 x라고 한다. 단, 첫 번째와 마지막 에너지 구슬은 고를 수 없다.
     * x번째 에너지 구슬을 제거한다.
     * Wx-1 × Wx+1의 에너지를 모을 수 있다.
     * N을 1 감소시키고, 에너지 구슬을 1번부터 N번까지로 다시 번호를 매긴다. 번호는 첫 구슬이 1번, 다음 구슬이 2번, ... 과 같이 매겨야 한다.
     * 구현.
     * num: 현재 구슬의 수, sum: 모은 에너지의 합(선택한 구슬이 x번이면 (x -1번 구슬의 무게) * (x + 1번 구슬의 무게))
     */
    public static void dfs(int num, int sum) {
        if(num == 2) { // 첫 번째와 마지막 구슬은 선택할 수 없으므로
            sumEnergy = sumEnergy > sum ? sumEnergy : sum;
            return;
        }

        int size = weights.size() - 1;
        for(int i = 1; i < size; i++) { // 첫 번째와 마지막 구슬은 선택 불가.
            int calcEnergy = weights.get(i - 1) * weights.get(i + 1); // 모은 에너지 계산.

            int removeTarget = weights.get(i); // 제거할 구슬을 임시 저장.(뒤에서 다시 원래 자리에 넣어주어야 함)
            weights.remove(i); // 구슬 제거

            dfs(num - 1, sum + calcEnergy); // 다음 depth 진행

            weights.add(i, removeTarget); // 제거한 구슬 원복.
        }
    }
}
