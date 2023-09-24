import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 27977. 킥보드로 등교하기
 */
public class boj27977 {
    /**
     * L: 학교까지의 거리, N: 킥보드 충전소의 수, K: 최대 충전소 방문 횟수,
     * maxDistance: 이전에 들렸던 충전소에서 현재 충전소까지의 최대 거리,
     * batteryCapacity: 구하는 배터리 용량
     */
    static int L, N, K, maxDistance, batteryCapacity;
    /**
     * chargingStations: 충전소의 위치
     */
    static int[] chargingStations;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        chargingStations = new int[N + 1];
        chargingStations[N] = L;
        int prevStation = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            chargingStations[i] = Integer.parseInt(st.nextToken());
            maxDistance = Math.max(maxDistance, chargingStations[i] - prevStation);
            prevStation = chargingStations[i];
        }
        maxDistance = Math.max(maxDistance, L - prevStation);

        parametricSearch();

        System.out.println(batteryCapacity);
    }

    public static void parametricSearch() {
        batteryCapacity = L;
        int low = maxDistance; // 목적지(학교)까지 도착은 할 수 있어야 함.
        int high = L;

        while(low <= high) {
            int mid = (low + high) / 2;

            if(getVisitCnt(mid) <= K) { // K번 이하로 충전소 방문하여 목적지 도착. 배터리 용량 줄여서 시도.
//                batteryCapacity = Math.min(batteryCapacity, mid);
                batteryCapacity = mid;
                high = mid - 1;

            } else {
                low = mid + 1;
            }
        }
    }

    private static int getVisitCnt(int mid) {
        int nowBattery = mid;
        int stationLocation = 0; // 현재 충전소의 위치
        int visitCnt = 0;
        for(int i = 0; i < N + 1; i++) {
            if(chargingStations[i] - stationLocation > nowBattery) { // 다음 충전소까지 현재 배터리로 갈 수 없는 경우 현재 충전소에서 충전
                nowBattery = mid;
                visitCnt++;
            }
            nowBattery -= (chargingStations[i] - stationLocation); // 다음 충전소까지 간 것으로 설정하기 위해
            stationLocation = chargingStations[i]; // 현재 충전소 위치 갱신
        }
        return visitCnt;
    }
}
