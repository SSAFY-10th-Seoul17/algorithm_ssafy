import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [boj] 27896. 특별한 서빙
 */
public class boj27896 {
    /**
     * N: 학생의 수, M: 가지 운동이 일어나는 불만도의 임계점,
     * dissatisfactionSum: 불만도의 합,
     * minEggplantCnt: 가지 운동을 일으키지 않게 하기 위한 가지의 최소 개수
     */
    static int N, M, dissatisfactionSum, minEggplantCnt;
    static PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> b - a);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        while(N-- > 0) {
            int dissatisfactionValue = Integer.parseInt(st.nextToken());

            priorityQueue.offer(dissatisfactionValue);
            dissatisfactionSum += dissatisfactionValue;

            while(dissatisfactionSum >= M) {
                minEggplantCnt++;
                dissatisfactionSum -= priorityQueue.poll() * 2;
            }
        }

        System.out.println(minEggplantCnt);
    }
}
