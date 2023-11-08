package study10월5주차목;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class boj3151_합이0 {
    static int n;
    static int[] arr;
    static long[] counter = new long[20001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        Set<Integer> removeDuplicate = new TreeSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            counter[num+10000]++; // 널포인터 검사하기
            removeDuplicate.add(num);
        }
        arr = new int[removeDuplicate.size()];
        int i = 0;
        for (int num: removeDuplicate) {
            arr[i++] = num;
        }

        long ans = 0;
        for (i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                ans += counter[arr[i]+10000] * (counter[arr[i]+10000] - 1) * (counter[arr[i]+10000] - 2) / 6;
                break;
            }
            if (arr[i] > 0)
                break;
            if (i + 1 <= arr.length-1)
                ans += count(i + 1, arr.length-1, arr[i]);
            if (counter[arr[i] + 10000] >= 2) {
                ans += count2(i);
            }
        }
        System.out.println(ans);
    }


    private static long count(int left, int right, int target) {
        
        target *= -1;
//        System.out.println("target : " + target);
        long cnt = 0L;
        int sum;
        while (left <= right) {
            sum = arr[left] + arr[right];
            if (left == right) {
                if (sum == target) {
                    cnt += counter[arr[left]+10000] * (counter[arr[left]+10000] - 1) / 2;
//                    System.out.println("left: " + arr[left] + ", 갯수: " + counter[arr[left]+10000]);
//                    System.out.println("right: " + arr[right] + ", 갯수: " + (counter[arr[right]+10000] - 1));
                }
                break;
            }
            if (sum == target) {
                cnt += counter[arr[left]+10000] * counter[arr[right]+10000];
//                System.out.println("left: " + arr[left] + ", 갯수: " + counter[arr[left]+10000]);
//                System.out.println("right: " + arr[right] + ", 갯수: " + counter[arr[right]+10000]);
                left++;
            }
            else if (sum < target) {
                left++;
            }
            else {
                right--;
            }
        }
//        System.out.println("target 갯수: " + counter[target * -1 + 10000]);
//        System.out.println("total : " + (cnt * counter[target * -1 + 10000]));
//        System.out.println();
        return cnt * counter[target * -1 + 10000];
    }

    private static long count2(int i) {
        if (2 * arr[i] * -1 + 10000 < 20001)
            return counter[arr[i]+10000] * (counter[arr[i]+10000] - 1) / 2 * counter[2 * arr[i] * -1 +10000];
        else
            return 0;
    }

}