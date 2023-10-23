import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * [boj] 2352. 반도체 설계
 */
public class boj2352 {
    /**
     * n: 포트의 수
     */
    static int n;
    static ArrayList<Integer> lisPortList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++) {
            int portNum = Integer.parseInt(st.nextToken());
            int idx = lisBinarySearch(portNum);

            if(idx >= lisPortList.size()) {
                lisPortList.add(portNum);
            } else {
                lisPortList.set(idx, portNum);
            }
        }

        System.out.println(lisPortList.size());
    }

    /**
     * 이분 탐색(Lower Bound) + LIS 알고리즘
     * @param portNum
     * @return
     */
    public static int lisBinarySearch(int portNum) {
        int high = lisPortList.size();
        int low = 0;

        while(low < high) {
            int mid = (low + high) / 2;

            if(lisPortList.get(mid) < portNum) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return high;
    }
}
