import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj16401 {

    static int [] cookies;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        cookies = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            cookies[i] = Integer.parseInt(st.nextToken());
        }
        solve(M);
    }

    private static void solve(int M) {

        Arrays.sort(cookies);

        int high = cookies[cookies.length-1];
        int low = 1;
        int mid = 0;

        while (high >= low) {

            mid = (high + low)/2;

            if(count(mid) >= M){
                low = mid + 1;
            }else if(count(mid) < M){
                high = mid - 1;
            }
        }

        System.out.println(high);
    }

    private static int count(int mid) {
        int sum = 0;

        for (int i = cookies.length - 1; i >= 0; i--) {
            sum += cookies[i]/mid;
        }
        return sum;
    }
}
