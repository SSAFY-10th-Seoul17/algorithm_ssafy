import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [boj] 2661. 좋은수열
 */
public class boj2661 {
    /**
     * MIN, MAX: 수열은 숫자 1~3으로만 이루어짐.
     */
    static final int MIN = 1, MAX = 3;
    /**
     * N: 만들려는 수열의 길이
     */
    static int N;
    /**
     * findCheck: 가장 작은 수를 나타내는 수열을 찾았는가
     */
    static boolean findCheck;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        simulate(new StringBuilder());
    }

    public static void simulate(StringBuilder sequence) {
        if(findCheck) {
            return;
        }

        if(sequence.length() == N) {
            System.out.println(sequence.toString());
            findCheck = true;

            return;
//            System.exit(0);
        }

        for(int nowNum = MIN; nowNum <= MAX; nowNum++) {
            StringBuilder newSequence = new StringBuilder(sequence).append(nowNum);

            if(makeGoodSequence(newSequence)) {
                simulate(newSequence);
            }
        }
    }

    public static boolean makeGoodSequence(StringBuilder sequence) {
        int sequenceLength = sequence.length();
        int halfSequenceLength = sequenceLength >> 1;

        for(int i = 1; i <= halfSequenceLength; i++) { // 확인할 문자열의 길이는 1 ~ 전체 문자열의 길이의 반
            String backPart = sequence.substring(sequenceLength - i, sequenceLength); // 맨 마지막 문자를 포함하는 i 길이의 문자열
            String frontPart = sequence.substring(sequenceLength - i * 2, sequenceLength - i); // backPart의 i 길이 앞 문자열

            if(backPart.equals(frontPart)) { // 두 부분 수열이 동일하면 나쁜 수열
                return false;
            }
        }
        return true;
    }
}
