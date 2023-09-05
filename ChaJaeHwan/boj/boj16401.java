import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class boj16401 {

    static int M, N;
    static Integer[] snacks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        snacks = new Integer[N];
        st = new StringTokenizer(br.readLine());

        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            snacks[i] = n;
            max = Math.max(n, max);
        }
        Arrays.sort(snacks);

        int answer = 0;

        int left = 1;
        int right = snacks[snacks.length - 1];

        while (left <= right) {
            int count = 0;
            int mid = (left + right) / 2;
            for (int i = 0; i < N; i++) {
                count += snacks[i] / mid;
            }
            if (count >= M) {
                answer = mid;
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }

        System.out.println(answer);

    }


}
