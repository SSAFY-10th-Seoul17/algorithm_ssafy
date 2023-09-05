import java.io.*;
import java.util.*;

public class Boj1477 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] distances = new int[n + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            distances[i] = Integer.parseInt(st.nextToken());
        }
        distances[n] = l;
        Arrays.sort(distances);

        System.out.println(getMinDistances(distances, m));
    }

    private static int getMinDistances(int[] distances, int m) {
        int prev = 0;
        int right = 0;
        for (int i = 0; i < distances.length; i++) {
            int point = distances[i];
            distances[i] -= prev;
            right = Math.max(right, distances[i]);
            prev = point;
        }
        
        int left = 1;

        while (left <= right) {
            int mid = (left + right) >> 1;

            if (isPossible(distances, mid, m)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static boolean isPossible(int[] distances, int value, int count) {
        for (int distance : distances) {
            count -= (distance - 1) / value;
            if (count < 0) {
                return false;
            }
        }

        return true;
    }
}

