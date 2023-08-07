import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Main main = new Main();
        System.out.println(main.MaxJoy(n));
    }

    int MaxJoy (int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        return (n/2) * (n - (n /2)) + MaxJoy(n/2) + MaxJoy(n - (n/2));
    }
}
