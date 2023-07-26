import java.util.*;

public class Main {
    static int N;
    public static void makeZero(int curr, int temp, int sum, String ans){
        curr += 1;
        if(curr > N){
            sum += temp;
            //System.out.println(ans + "   " + sum);
            if(sum == 0) System.out.println(ans);
            return;
        }
        // no + -
        makeZero(curr, temp * 10 + (int) (temp / Math.abs(temp)) * curr, sum, ans + " " + String.valueOf(curr));
        // + num
        makeZero(curr, curr, sum + temp, ans + "+" + String.valueOf(curr));
        // - num
        makeZero(curr, -curr, sum + temp, ans + "-" + String.valueOf(curr));
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testcase = scanner.nextInt();
        for(int t = 0; t < testcase; t++){
            N = scanner.nextInt();
            makeZero(1, 1, 0,"1");
            System.out.println();
        }
    }
}
