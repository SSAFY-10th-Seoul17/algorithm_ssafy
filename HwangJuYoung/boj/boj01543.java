import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String doc = sc.nextLine();
        String str = sc.nextLine();

        int cnt = 0;

        for (int i = 0; i <= doc.length() - str.length(); i++) {
            String temp = "";
            for (int j = 0; j < str.length(); j++) {
                temp += doc.charAt(i+j);
            }
            if (temp.equals(str)) {
                cnt++;
                i += str.length() - 1;
            }
        }

        System.out.println(cnt);
    }
}
