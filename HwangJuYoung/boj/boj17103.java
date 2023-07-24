import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        Main main = new Main();

        int[] PrimeNum = new int[1000000];

        for (int i = 0; i < PrimeNum.length; i++) {
            if (main.IsPrimeNum(i)) {
                PrimeNum[i] = 1;
            }
        }

        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();
            int cnt = 0;
            for (int j = 2; j <= N / 2; j++) {
                if (PrimeNum[j] == 1) {
                    if (PrimeNum[N-j] == 1) {
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
        }
    }

    boolean IsPrimeNum(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
