import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 2792. 보석 상자
 */
public class boj2792 {
    /**
     * N: 아이들의 수, M: 색상의 수, maxCount: 같은 색 보석의 최대 수, minEnvy: 질투심의 최솟값
     */
    static int N, M, maxCount = 0, minEnvy = Integer.MAX_VALUE;
    /**
     * jewelryCntByColor: 보석 색상에 따른 개수
     */
    static int[] jewelryCntByColor;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        jewelryCntByColor = new int[M];
        for(int i = 0 ; i < M; i++) {
            jewelryCntByColor[i] = Integer.parseInt(br.readLine());
            if(maxCount < jewelryCntByColor[i]) {
                maxCount = jewelryCntByColor[i];
            }
        }

        parametricSearch();

        System.out.println(minEnvy);
    }

    public static void parametricSearch() {
        int low = 1;
        int high = maxCount;

        while(low <= high) {
            int mid = (low + high) / 2;

            if(divPeople(mid) <= N) {
                high = mid - 1;
                minEnvy = mid;
            } else {
                low = mid + 1;
            }
        }
    }

    /**
     * parameter로 들어온 질투심(= 가장 많은 보석을 가져간 학생이 가진 보석의 수)을 가정. 이때 몇 명이 나눠 가지나.
     * @param envy : 질투심
     * @return
     */
    public static int divPeople(int envy) {
        int divPeople = 0;
        for(int jewelryCnt: jewelryCntByColor) {
            // 모든 보석을 다 나누어 주어야 한다.
            divPeople += jewelryCnt % envy == 0 ? jewelryCnt / envy : jewelryCnt / envy + 1;
        }

        return divPeople;
    }
}
