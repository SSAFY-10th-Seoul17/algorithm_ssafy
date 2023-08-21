import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());

        int totalFive = cntDiv(n, 5) - cntDiv(n-m, 5) - cntDiv(m, 5);
        int totalTwo = cntDiv(n, 2) -cntDiv(n-m, 2) - cntDiv(m, 2);
        System.out.println(Math.min(totalFive, totalTwo));
        br.close();
    }

    public static int cntDiv(int n, int div) {
        int cnt = 0;
        while(n > 0) {
            cnt += n / div;
            n /= div;
        }
        return cnt;
    }
}