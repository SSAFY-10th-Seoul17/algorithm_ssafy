import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 14719. 빗물
 */
public class boj14719 {
    /**
     * H: 2차원 세계의 세로 길이, W: 2차원 세계의 가로 길이
     * (1 ≤ H, W ≤ 500)
     * stagnantRainwater: 고인 빗물의 총량
     */
    static int H, W, stagnantRainwater;
    /**
     * height: 블록이 쌓이 높이의 배열
     */
    static int[] height;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        height = new int[W];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < W; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        sol();

        System.out.println(stagnantRainwater);
    }

    /**
     * 빗물이 고이기 위한 조건
     * 1. 첫 번째와, 마지막 블록에는 빗물(인덱스 0, W - 1)이 고일 수 없다.
     * 2. 현재 블록의 높이보다 높은 블록이 왼쪽에 있어야 한다.
     * 3. 현재 블록의 높이보다 높은 블록이 오른쪽에 있어야 한다.
     *
     * -> 현재 인덱스를 기준으로 왼쪽에서 가장 높은 블록과 오른쪽에서 가장 높은 블록을 구한다.
     * 현재 블록이 두 블록보다 낮은지 확인한다.
     * 낮다면 둘 중에 더 낮은 블록을 기준으로 낮은 블록에서 현재 블록의 높이를 차를 이용해 빗물이 고일 수 있는 높이를 계산한다.
     * 이 값을 누적하면 최종 결과이다.
     */
    public static void sol() {
        int size = W - 1;
        for(int i = 1; i < size; i++) {
            int leftHeight = 0;
            int rightHeight = 0;

            for(int j = 0; j < i; j++) {
                leftHeight = Math.max(leftHeight, height[j]);
            }

            for(int j = i + 1; j < W; j++) {
                rightHeight = Math.max(rightHeight, height[j]);
            }

            if(height[i] < leftHeight && height[i] < rightHeight) {
                stagnantRainwater += Math.min(leftHeight, rightHeight) - height[i];
            }
        }
    }
}
