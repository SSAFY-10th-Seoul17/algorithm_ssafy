import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static class Cal{
        int r, c;
        char op;
        public Cal(int r, int c, char op) {
            this.r = r;
            this.c = c;
            this.op = op;
        }
    }
    public static int n, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    public static char[][] map;
    public static int[][] direction = {{1,0}, {0,1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = String.join("", br.readLine().split(" ")).toCharArray();
        }
        dfs(new Cal(0, 0, '.'), map[0][0] - '0');
        System.out.println(max + " " + min);
        br.close();
    }

    public static void dfs(Cal cal, int sum) {
        if(cal.r == n-1 && cal.c == n-1) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        for (int i = 0; i < direction.length; i++) {
            int nextR = cal.r + direction[i][0];
            int nextC = cal.c + direction[i][1];

            if(checkPos(nextR, nextC)) {
                if(Character.isDigit(map[nextR][nextC])) {  // 숫자
                    int num = map[nextR][nextC] - '0';
                    Cal c = new Cal(nextR, nextC, '.');
                    if (cal.op == '+') dfs(c, sum + num);
                    else if(cal.op == '-') dfs(c, sum - num);
                    else dfs(c, sum * num);
                }
                else dfs(new Cal(nextR, nextC, map[nextR][nextC]), sum);
            }
        }
    }

    public static boolean checkPos(int r, int c) {
        return (r<0 || r>=n || c<0 || c>=n) ? false : true;
    }

}
