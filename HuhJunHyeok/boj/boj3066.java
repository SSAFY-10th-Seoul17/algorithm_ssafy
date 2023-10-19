import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * [boj] 3066. 브리징 시그널
 */
public class boj3066 {
    /**
     * T: 테스트 케이스의 수, N: 포트의 수
     */
    static int T, N;
    static ArrayList<Integer> lisPort;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            lisPort = new ArrayList<>();

            N = Integer.parseInt(br.readLine());
            while(N-- > 0) {
                int portNum = Integer.parseInt(br.readLine());
                int idx = lisBinarySearch(portNum);
                if(idx >= lisPort.size()) {
                    lisPort.add(portNum);
                } else {
                    lisPort.set(idx, portNum);
                }
            }
            sb.append(lisPort.size()).append("\n");
        }
        System.out.print(sb.toString());
    }

    public static int lisBinarySearch(int portNum) {
        int high = lisPort.size();
        int low = 0;

        while(low < high) {
            int mid = (low + high) >> 1;

            if(lisPort.get(mid) < portNum) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return high;
    }
}
