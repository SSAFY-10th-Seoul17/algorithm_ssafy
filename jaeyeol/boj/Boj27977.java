import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj27977 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int l = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int[] distances = new int[n + 1];

        int prev = 0;
        for (int i = 0; i < n; i++) {
            int cur = Integer.parseInt(st.nextToken());
            distances[i] = cur - prev;
            prev = cur;
        }
        distances[n] = l - prev;

        System.out.println(getMinBattery(distances, k, l));
    }

    private static int getMinBattery(int[] distances, int k, int right) {
        int left = Arrays.stream(distances).max().getAsInt();

        while (left <= right) {
            int mid = (left + right) >> 1;

            if (isPossible(distances, k, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static boolean isPossible(int[] distances, int k, int battery) {
        int spentBattery = 0;

        for (int distance : distances) {
            spentBattery += distance;
            if (spentBattery > battery) {
                if (--k < 0) {
                    return false;
                }

                spentBattery = distance;
            }
        }

        return true;
    }

}


