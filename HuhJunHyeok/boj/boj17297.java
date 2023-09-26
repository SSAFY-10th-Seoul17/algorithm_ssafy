import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * [boj] 17297. Messi Gimossi
 */
public class boj17297 {
    /**
     * M: 출력하려는 글자가 몇 번째 글자인지
     * (1 ≤ M ≤ 2^30 - 1)
     */
    static int M;
    /**
     * sequenceLength: 수열의 n항의 길이를 저장하는 리스트
     */
    static ArrayList<Integer> sequenceLength = new ArrayList<>();
    static final String WHITE_SPACE_OUT = "Messi Messi Gimossi";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        M = Integer.parseInt(br.readLine());

        sequenceLength.add(0); // 0항은 0. 사용하지 않음.
        sequenceLength.add(5); // 1항. "Messi"
        sequenceLength.add(13); // 2항. "Messi Gimossi"
        int idx = 2;
        while(M > sequenceLength.get(idx)) {
            int nowSequenceLength = sequenceLength.get(idx);
            int prevSequenceLength = sequenceLength.get(idx - 1);
            int nextSequenceLength = nowSequenceLength + prevSequenceLength + 1; // n항은 (n - 1항) 공백 (n - 2항)
            sequenceLength.add(nextSequenceLength);
            idx++;
        }

        findOut(idx, M);
    }

    public static void findOut(int idx, int m) {
        while(sequenceLength.get(idx) < m) {
            idx++;
        }

        if(idx <= 2) { // 기저 조건
            switch (m) {
                case 1:
                    System.out.println("M");
                    break;
                case 2:
                    System.out.println("e");
                    break;
                case 3:
                case 4:
                case 11:
                case 12:
                    System.out.println("s");
                    break;
                case 5:
                case 8:
                case 13:
                    System.out.println("i");
                    break;
                case 6:
                    System.out.println(WHITE_SPACE_OUT);
                    break;
                case 7:
                    System.out.println("G");
                    break;
                case 9:
                    System.out.println("m");
                    break;
                case 10:
                    System.out.println("o");
                    break;
            }
            return;
        }

        m -= sequenceLength.get(idx - 1);
        if(m == 1) {
            System.out.println(WHITE_SPACE_OUT);
        } else {
            findOut(1, --m);
        }
    }
}
