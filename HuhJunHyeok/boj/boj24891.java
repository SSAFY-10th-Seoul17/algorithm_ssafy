import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [boj] 24891. 단어 마방진
 */
public class boj24891 {
    /**
     * L: 단어의 길이, N: 단어의 수
     */
    static int L, N;
    static String[] words, magicSquareWords;
    static int[] pickIndex;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        words = new String[N];
        for(int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        Arrays.sort(words);

        magicSquareWords = new String[N];
        pickIndex = new int[N];
        visited = new boolean[N];

        dfs(0);

        System.out.println("NONE");
    }

    public static void dfs(int pickCnt) {
        if(pickCnt == L) {
            for(int i = 0; i < L; i++) {
                magicSquareWords[i] = words[pickIndex[i]];
            }

            if(possibleMagicSqare()) {
                for(int i = 0; i < L; i++) {
                    sb.append(magicSquareWords[i]).append("\n");
                }

                System.out.print(sb.toString());

                System.exit(0);
            }

            return;
        }

        for(int i = 0; i < N; i++) {
            if(!visited[i]) {
                pickIndex[pickCnt] = i;
                visited[i] = true;
                dfs(pickCnt + 1);
                visited[i] = false;
            }
        }
    }

    public static boolean possibleMagicSqare() {
        for(int i = 0; i < L; i++) {
            for(int j = 0; j < L; j++) {
                if(magicSquareWords[i].charAt(j) != magicSquareWords[j].charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }
}
