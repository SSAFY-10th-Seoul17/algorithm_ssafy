import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int[] arr = new int[t];

        int max = 0;
        for (int i = 0; i < t; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }

        List<Integer> primes = getPrimes(max);

        StringBuilder result = new StringBuilder();
        for (int n : arr) {
            result.append(getGoldCount(primes, n)).append("\n");
        }
        System.out.print(result);
    }

    private static List<Integer> getPrimes(int max) {
        List<Integer> primes = new ArrayList<>();

        boolean[] isPrime = new boolean[max + 1];

        int range = (int) Math.sqrt(max);
        for (int i = 2; i <= range; i++) {
            if (isPrime[i]) {
                continue;
            }

            for (int j = i * 2; j <= max; j += i) {
                isPrime[j] = true;
            }
        }

        for (int i = 2; i < isPrime.length; i++) {
            if (!isPrime[i]) {
                primes.add(i);
            }
        }
        return primes;
    }


    static int getGoldCount(List<Integer> primes, int n) {
        int left = 0;
        int right = Collections.binarySearch(primes, n);
        if (right < 0) {
            right = -(right + 2);
        }

        int count = 0;

        while (left <= right) {
            int sum = primes.get(left) + primes.get(right);

            if (sum < n) {
                left++;
            } else if (sum > n) {
                right--;
            } else {
                count++;
                left++;
                right--;
            }
        }

        return count;
    }

}

