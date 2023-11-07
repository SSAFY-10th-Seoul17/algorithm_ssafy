import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [boj] 3151. 합이 0
 */
public class boj3151 {
    /**
     * N: 학생의 수
     */
    static int N;
    static int[] abilities;
    static long pickCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        abilities = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            abilities[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(abilities);

        for(int i = 0; i < N; i++) {
            if(abilities[i] > 0) { // 합이 0을 만들어야 하는데, 이 숫자만으로 0을 넘어섬.
                break;
            }

            int low = i + 1;
            int high = N - 1;

            while(low < high) {
                int sum = abilities[i] + abilities[low] + abilities[high];

                if(sum == 0) {
                    int lowCount = 1;
                    int highCount = 1;

                    if(abilities[low] == abilities[high]) {
                        int sameCount = high - low + 1;
                        pickCount += (long) sameCount * (sameCount - 1) / 2;
                        break;
                    }

                    while(abilities[high] == abilities[high - 1]) {
                        highCount++;
                        high--;
                    }
                    while(abilities[low] == abilities[low + 1]) {
                        lowCount++;
                        low++;
                    }

                    pickCount += (long) lowCount * highCount;
                }

                if(sum < 0) {
                    low++;
                } else {
                    high--;
                }
            }
        }

        System.out.println(pickCount);
    }
}
