import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [boj] 1697. 숨바꼭질
 */
public class boj1697 {
    static int n, k, maxLocation = 100_000;
    static int[] visitedWithCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        visitedWithCount = new int[maxLocation + 1];

        if(n == k) {
            System.out.println(0);
        } else if(n > k) {
            System.out.println(n - k);
        } else {
            int minCount = bfs(n);
            System.out.println(minCount);
        }
    }

    public static int bfs(int location) {
        Queue<Integer> locationQueue = new ArrayDeque<>();

        locationQueue.add(location);
        visitedWithCount[location] = 1; // int형 배열의 원소는 0으로 초기화되기 때문에.

        while(!locationQueue.isEmpty()) {
            int nowLocation = locationQueue.poll();

            if(nowLocation == k) {
                return visitedWithCount[nowLocation] - 1;
            }

            int minusOne = nowLocation - 1, plusOne = nowLocation + 1, multiplyTwo = nowLocation * 2;

            if(minusOne >= 0 && visitedWithCount[minusOne] == 0) {
                visitedWithCount[minusOne] = visitedWithCount[nowLocation] + 1;
                locationQueue.add(minusOne);
            }

            if(plusOne <= maxLocation && visitedWithCount[plusOne] == 0) {
                visitedWithCount[plusOne] = visitedWithCount[nowLocation] + 1;
                locationQueue.add(plusOne);
            }

            if(multiplyTwo <= maxLocation && visitedWithCount[multiplyTwo] == 0) {
                visitedWithCount[multiplyTwo] = visitedWithCount[nowLocation] + 1;
                locationQueue.add(multiplyTwo);
            }
        }
        return -1; // 도달 X.
    }
}
