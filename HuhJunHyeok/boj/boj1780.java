import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * [boj] 1780. 종이의 개수
 */
public class boj1780 {
    /**
     * N: 행렬의 행과 열의 크기
     *
     */
    static int N;
    /**
     * paperCnt: 해당 숫자로만 채워진 종이의 수.
     * paperCnt[n] == n-1으로만 채워진 종이의 수
     */
    static int[] paperCnt = new int[3];
//    static HashMap<Integer, Integer> paperCnt = new HashMap<>(3);
    /**
     * matrix: 행렬. 각 칸은 -1, 0, 1 중 하나의 값을 가짐.
     */
    static int[][] matrix;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        matrix = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divideAndConquer(N, 0, 0);

        for(int i = 0; i < 3; i++) {
            sb.append(paperCnt[i]).append("\n");
        }

        System.out.print(sb.toString());
    }

    /**
     * 분할 정복
     * @param size 확인하는 구역의 행(열)의 크기
     * @param r 확인하는 구역의 좌측 상단의 행 좌표
     * @param c 확인하는 구역의 좌측 상단의 열 좌표
     */
    public static void divideAndConquer(int size, int r, int c) {
        if(isSameValuePaper(size, r, c)) {
            int nowValue = matrix[r][c];
            paperCnt[nowValue + 1]++;
        } else {
            int newSize = size / 3;

            // 좌상, 상, 우상, 좌중, 중중, 우중, 좌하, 하, 우하 순서로 재귀
//            divideAndConquer(newSize, r, c);
//            divideAndConquer(newSize, r, c + newSize);
//            divideAndConquer(newSize, r, c + newSizeMultiple);
//            divideAndConquer(newSize, r + newSize, c);
//            divideAndConquer(newSize, r + newSize, c + newSize);
//            divideAndConquer(newSize, r + newSize, c + newSizeMultiple);
//            divideAndConquer(newSize, r + newSizeMultiple, c);
//            divideAndConquer(newSize, r + newSizeMultiple, c + newSize);
//            divideAndConquer(newSize, r + newSizeMultiple, c + newSizeMultiple);
            for(int i = r, rSize = r + size; i < rSize; i += newSize) {
                for(int j = c, cSize = c + size; j < cSize; j += newSize) {
                    divideAndConquer(newSize, i, j);
                }
            }
        }
    }

    public static boolean isSameValuePaper(int size, int r, int c) {
        int nowValue = matrix[r][c];
        int endR = r + size;
        int endC = c + size;

        for(int i = r; i < endR; i++) {
            for(int j = c; j < endC; j++) {
                if(matrix[i][j] != nowValue) {
                    return false;
                }
            }
        }

        return true;
    }

}
