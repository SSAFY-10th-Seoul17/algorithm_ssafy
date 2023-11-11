import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [boj] 30108. 교육적인 트리 문제
 */
public class boj30108 {
    /**
     *
     */
    static int N;
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

//        st = new StringTokenizer(br.readLine(), " ");
        String noUseInput = br.readLine();
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            maxHeap.offer(Integer.parseInt(st.nextToken()));
        }

        Long sum = 0L;
        for(int i = 1; i <= N; i++) {
            sum += maxHeap.poll();
            sb.append(sum).append("\n");
        }

        System.out.print(sb.toString());
    }
}
