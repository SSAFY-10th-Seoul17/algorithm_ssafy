import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * [boj] 5904. Moo 게임
 */
public class boj5904 {
    /**
     * N: 출력하려는 글자가 몇 번째 글자인지
     * 1 <= N <= 10^9
     */
    static int N;
    /**
     * mooSequenceLengths: Moo 수열의 n항의 길이를 저장하는 리스트
     */
    static ArrayList<Integer> mooSequenceLengths = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        mooSequenceLengths.add(0); // 0항은 0. 사용하지 않음.
        mooSequenceLengths.add(3); // 1항은 3. "moo"
        int idx = 1;
        while(N > mooSequenceLengths.get(idx)) {
            int nowSequenceLength = mooSequenceLengths.get(idx);
            int nextSequenceLength = nowSequenceLength * 2 + idx + 3;
            mooSequenceLengths.add(nextSequenceLength);
            idx++;
        }

        mooGame(idx, N);
    }

    /**
     * N이 3 이하인 경우 "moo"에 따른 값 출력
     * len(S(k)) = len(S(k - 1)) * 2 + k + 3
     * len * 2 + k + 3이 N보다 커질때까지 재귀를 반복 or 반복문
     * 커진 경우 N은 절대로 1 ~ len 사이의 숫자가 아님
     * 따라서, len ~ len + k + 3 사이의 수인지 확인(좌측 경계값 미포함). 그 사이의 수이면 len + 1인 경우 "m" 나머지 경우 "o"을 출력 후 종료.
     * 그 사이의 수가 아니면 N은 뒤쪽(우측) 부분의 S(k - 1)에 위치한 것
     * 따라서, 우측 S(k - 1)에서 어느 위치인지 찾기 위해 재귀 호출 필요.
     * N = N - (len + k + 3) 이고, k = 1, len = 3인 경우로 재귀 호출.
     *
     * 위의 과정을 적용 및 리팩토링.
     */
    public static void mooGame(int idx, int n) {
        while(mooSequenceLengths.get(idx) < n) {
            idx++;
        }

        n -= mooSequenceLengths.get(idx - 1);
        if(n <= idx + 2) { // idx + 2 => (idx - 1) + 3
            System.out.println(n == 1 ? "m" : "o");
        } else {
            n -= idx + 2;
            mooGame(1, n);
        }
    }
}

//public class boj5904 {
//    /**
//     * N: 출력하려는 글자가 몇 번째 글자인지
//     * 1 <= N <= 10^9
//     */
//    static int N;
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        N = Integer.parseInt(br.readLine());
//
//        mooGame(N, 1, 3);
//    }
//
//    /**
//     * N이 3 이하인 경우 "moo"에 따른 값 출력
//     * len(S(k)) = len(S(k - 1)) * 2 + k + 3
//     * len * 2 + k + 3이 N보다 커질때까지 재귀를 반복 or 반복문
//     * 커진 경우 N은 절대로 1 ~ len 사이의 숫자가 아님
//     * 따라서, len ~ len + k + 3 사이의 수인지 확인(좌측 경계값 미포함). 그 사이의 수이면 len + 1인 경우 "m" 나머지 경우 "o"을 출력 후 종료
//     * 그 사이의 수가 아니면 N은 뒤쪽(우측) 부분의 S(k - 1)에 위치한 것
//     * 따라서, 우측 S(k - 1)에서 어느 위치인지 찾기 위해 재귀 호출 필요.
//     * N = N - (len + k + 3) 이고, k = 1, len = 3인 경우로 재귀 호출.
//     */
//    public static void mooGame(int n, int k, int len) {
//        if(n <= 3) {
//            System.out.println(n == 1 ? "m" : "o");
//            System.exit(0);
//        }
//
//        if(n > len * 2 + k + 3) { // 아직 n 위치의 값을 확인할 수 없어서 다음 수열을 확인해야 함.
//            mooGame(n, k + 1, len * 2 + k + 3);
//        } else if(n > len && n <= len + k + 3) { // 이번 수열의 좌측~중앙 부분에 n 위치가 있는 경우
//            if(n == len + 1) {
//                System.out.println("m");
//            } else {
//                System.out.println("o");
//            }
//            System.exit(0);
//        } else { // 이번 수열의 우측에서 어느 위치의 값인지 찾기
//            mooGame(n - (len + k + 3), 1, 3);
//        }
//
//    }
//}
