import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [boj] 1477. 휴게소 세우기
 */
public class boj1477 {
    /**
     * N: 현재 휴게소의 수, M: 더 지으려고 하는 휴게소의 수, L: 고속도로의 길이,
     * minDistanceBetweenRestArea: 휴게소 사이의 거리의 최댓값의 최솟값
     * size: restAreas 배열의 길이
     */
    static int N, M, L, minDistanceBetweenRestArea, size;
    /**
     * restAreas: 현재 휴게소의 위치 배열
     */
    static int[] restAreas;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        restAreas = new int[N + 2]; // 휴게소의 위치는 1~L-1 사이의 값. 0번과 N + 1번의 인덱스는 각각 휴게소가 올 수 없는 위치인 0과 L로 설정.
        for(int i = 1; i <= N; i++) {
            restAreas[i] = Integer.parseInt(st.nextToken());
        }
        restAreas[N + 1] = L;
        size = restAreas.length;

        parametricSearch();

        System.out.println(minDistanceBetweenRestArea);
    }

    /**
     * 이분 탐색의 최적화 문제를 매개 변수 탐색의 결정 문제로.
     */
    public static void parametricSearch() {
        Arrays.sort(restAreas);

        int low = 1;
        int high = L - 1;

        while(low <= high) {
            int mid = (low + high) / 2;

            if(getCountBuildAreas(mid) <= M) {
                high = mid - 1;
                minDistanceBetweenRestArea = mid;
            } else {
                low = mid + 1;
            }
        }
    }

    /**
     * distance 거리를 기준으로 각 휴게소 사이에 총 몇 개의 휴게소를 설치할 수 있는가를 확인하는 함수
     * @param distance
     * @return
     */
    public static int getCountBuildAreas(int distance) {
        int count = 0;

        for(int i = 1; i < size; i++) {
            count += (restAreas[i] - restAreas[i - 1] - 1) / distance;
        }

        return count;
    }
}
