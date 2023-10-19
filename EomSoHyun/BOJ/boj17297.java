import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int m;
    static List<Integer> nums;
    static String str1 = "Messi";
    static String str2 = "Messi Gimossi";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        m = Integer.parseInt(br.readLine());

        nums = new ArrayList<>();
        nums.add(0);
        nums.add(5);
        nums.add(13);

        int i = 0;
        if (m > 13) {
            i = 3;
            while (true) {
                nums.add(nums.get(i - 1) + nums.get(i - 2) + 1);
                if (nums.get(i) >= m) break;
                i++;
            }

        }

        if (m <= 5) {
            i = 1;
        }
        else if (m <= 13) {
            i = 2;
        }

        Messi(m, i);

    }

    public static void Messi(int m, int i) {
        if (i == 1) {
            System.out.println(str1.charAt(m-1));
            return;
        }

        else if (i == 2) {
            char ch = str2.charAt(m-1);
            if (ch == ' ') {
                System.out.println("Messi Messi Gimossi");
            }
            else {
                System.out.println(ch);
            }
            return;
        }

        if (m == nums.get(i-1) + 1) {
            System.out.println("Messi Messi Gimossi");
            return;
        }

        if (m > nums.get(i-1)) {
            m = m - (nums.get(i-1) + 1);
            Messi(m, i-2);
        }

        else {
            Messi(m, i-1);
        }
    }

}
