import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1450 {
    static int[] arr;
    static int target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int capacity = 2 << ((n >> 1) - 1);

        List<Integer> list1 = new ArrayList<>(capacity);
        List<Integer> list2 = new ArrayList<>(n % 2 == 0 ? capacity : capacity << 1);
        numberOfCases(list1, 0, n / 2 - 1, 0);
        numberOfCases(list2, n / 2, n - 1, 0);
        Collections.sort(list2);

        int count = 0;
        for (Integer num : list1) {
            count += upperBound(list2, target - num);
        }

        System.out.println(count);
    }

    static int upperBound(List<Integer> list, int value) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = (left + right) >> 1;

            if (list.get(mid) <= value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private static void numberOfCases(List<Integer> list, int left, int right, int sum) {
        if (left > right) {
            list.add(sum);
            return;
        }
        if (sum > target) {
            return;
        }

        numberOfCases(list, left + 1, right, sum);
        numberOfCases(list, left + 1, right, sum + arr[left]);
    }

}

