import java.io.*;

public class boj17297 {

    static String ms = "Messi";
    static String msgs = "Messi Gimossi";
    static int[] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());

        dp = new int[46];

        dp[1] = 5;
        dp[2] = 13;

        int k = 3;

        if(m <= 5){
            k = 1;
        }else if(m <= 13){
            k = 2;
        }else{
            while(true){
                dp[k] = dp[k - 1] + 1 + dp[k - 2];
                if(dp[k] >= m) break;
                k++;
            }
        }

        char result = messiFeelGood(m, k);
        if(result == ' '){
            System.out.println("Messi Messi Gimossi");
        }else{
            System.out.println(result);
        }
    }

    private static char messiFeelGood(int m, int k){

        if(k == 1){
            return ms.charAt(m - 1);
        }

        if(k == 2){
            return msgs.charAt(m - 1);
        }

        int blankIdx = dp[k - 1] + 1;

        if(m < blankIdx){
            return messiFeelGood(m, k - 1);
        }else if(m == blankIdx){
            return ' ';
        }else{
            return messiFeelGood(m - blankIdx, k - 2);
        }
    }
}