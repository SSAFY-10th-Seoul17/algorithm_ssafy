import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [boj] 3020. 개똥벌레
 */
public class boj3020 {
    /**
     * N: 동굴의 길이, H: 동굴의 높이
     * (2 ≤ N ≤ 200,000, 2 ≤ H ≤ 500,000) & N은 항상 짝수.
     * minDestoryCnt: 파괴해야하는 장애물(석순, 종유석)의 최솟값
     * sectionCnt: minDestoryCnt 값을 가지는 구간의 수
     */
    static int N, H, minDestroyCnt, sectionCnt;
    /**
     * stalagmite: 석순
     * stalactite: 종유석
     * 석순과 종유석의 길이는 H보다 작은 양수
     */
    static int[] stalagmite, stalactite;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        /*
        누적 합으로 해당 구간에 존재하는 석순이나 종유석의 수를 구해서 탐색
         */
        int halfN = N / 2;
        int stalaHeight = H + 2; // 구간은 1~H 구간을 가짐. 구간 양 끝에서 하나씩 구간을 추가하고 그 구간은 사용하지 않음.
        stalagmite = new int[stalaHeight];
        stalactite = new int[stalaHeight];
        for(int i = 0; i < halfN; i++) {
            int stalagmiteHeight = Integer.parseInt(br.readLine());
            int stalactiteHeight = H - Integer.parseInt(br.readLine()) + 1;
            stalagmite[stalagmiteHeight]++;
            stalactite[stalactiteHeight]++;
        }

        /*
        석순은 아래에서 위로 자람. 따라서, 큰 인덱스의 값에서 작은 인덱스의 값으로 누적 합.
         */
        for(int i = H; i > 1; i--) {
            stalagmite[i - 1] += stalagmite[i];
        }

        /*
        종유석은 위에서 아래로 자람. 따라서, 작은 인덱스의 값에서 큰 익덱스의 값으로 누적 합.
         */
        for(int i = 1; i < H; i++) {
            stalactite[i + 1] += stalactite[i];
        }

        minDestroyCnt = N;
        for(int i = 1; i <= H; i++) {
            int destroyCnt = stalagmite[i] + stalactite[i];

            if(destroyCnt < minDestroyCnt) {
                minDestroyCnt = destroyCnt;
                sectionCnt = 1;
            } else if(destroyCnt == minDestroyCnt) {
                sectionCnt++;
            }
        }

        System.out.println(minDestroyCnt + " " + sectionCnt);
    }
}
