import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [boj] 3079. 입국심사
 */
public class boj3079 {
    /**
     * N: 입국심사대의 수, M: 입국심사를 받아야 하는 사람의 수
     */
    static int N, M;
    /**
     * reqTimes: 심사대별 심사 소요 시간
     */
    static int[] reqTimes;
    /**
     * maxTableTime: 심사 소요 시간이 가장 긴 심사대의 소요 시간(이 값은 int 범위이나 high 계산을 편하게 하기 위해 long으로)
     * minTotalTime: 심사를 마치는데 걸리는 시간의 최솟값
     */
    static long maxTableTime, minTotalTime = Long.MAX_VALUE;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        reqTimes = new int[N];
        for(int i = 0; i < N; i++) {
            reqTimes[i] = Integer.parseInt(br.readLine());
            maxTableTime = Math.max(maxTableTime, reqTimes[i]);
        }

//        Arrays.sort(reqTimes);
        parametricSearch();

        System.out.println(minTotalTime);
    }

    public static void parametricSearch() {
        long low = 0;
        long high = maxTableTime * M; // 최대 소요 시간 = 가장 긴 소요 시간 * 사람 수

        while(low <= high) {
            long mid = (low + high) / 2;

            long totalCount = 0; // mid 시간에 각 심사대에서 심사를 보는 사람 수의 합
            for(int tableTime : reqTimes) {
//                /*
//                심사대별 소요 시간의 배열인 reqTimes는 정렬된 상태.
//                현재 심사대의 심사 소요 시간이 확인 중인 소요 시간 mid보다 크거나
//                심사를 보는 사람의 수의 합이 총 인원 수 이상이면 break
//                 */
//                if(totalCount >= M || tableTime > mid) {
//                    break;
//                }

                // reqTimes 정렬 안 한 경우
                if(totalCount >= M) {
                    break;
                }

                // mid 시간에 tableTime의 소요 시간을 갖는 심사대에서 심사를 보는 사람의 수
                long count = mid / tableTime;


                totalCount += count;
            }

            /*
            입국심사를 받아야 하는 사람의 수 이상의 사람을 심사할 수 있으면 더 짧은 시간으로 확인해봐야 함.
             */
            if(totalCount >= M) {
                high = mid - 1;
                minTotalTime = Math.min(minTotalTime, mid);
            } else {
                low = mid + 1;
            }
        }
    }
}
