import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * [boj] 11037. 중복 없는 수
 */
public class boj11037 {
    static ArrayList<Integer> noOverlapNumList = new ArrayList<>();
    static int num, answerIdx;
    static int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    static boolean[] visited = new boolean[9];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 1; i <= 9; i++) {
            makeNoOverlapNum(0, 0, i);
        }
//        System.out.println(noOverlapNumList.toString());

        String strNum = "";
        while((strNum = br.readLine()) != null && (!strNum.isEmpty())) {
//            System.out.println("strNum" + strNum);
            num = Integer.parseInt(strNum);
//            System.out.println(num);
            if(num >= 987654321) {
                sb.append(0).append("\n");
            } else {
                upperBound();
                sb.append(noOverlapNumList.get(answerIdx)).append("\n");
//                System.out.println(noOverlapNumList.get(answerIdx));
            }
        }

        System.out.print(sb.toString());
    }

    /**
     * 순열을 이용하여 중복 없는 수를 모두 구함
     * @param nowLength
     * @param nowNum
     * @param minLength
     */
    public static void makeNoOverlapNum(int nowLength, int nowNum, int minLength) {
        if(nowLength >= minLength) {
            noOverlapNumList.add(nowNum);
        } else {
            for(int i = 0; i < 9; i++) {
                if(!visited[i]) {
                    visited[i] = true;
                    makeNoOverlapNum(nowLength + 1, nowNum * 10 + numbers[i], minLength);
                    visited[i] = false;
                }
            }
        }
    }

    /**
     * upperbound를 사용하는 bianry search로 탐색.
     */
    public static void upperBound() {
        int low = 0;
        int high = noOverlapNumList.size();

        while(low <= high) {
            int mid = (low + high) >> 1;

            if(noOverlapNumList.get(mid) <= num) {
                low = mid + 1;
            } else {
                answerIdx = mid;
                high = mid - 1;
            }
        }
    }
}
