
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1543 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = br.readLine();
        String search = br.readLine();
        int count = 0;

        for (int i = 0; i + search.length()-1 < first.length(); i++) {
            boolean check = true;
            for (int j = 0; j < search.length(); j++) {
                if (first.charAt(i + j) != search.charAt(j)) {
                    check = false;
                    break;
                }
            }

            if (check) {
                i += search.length() - 1;
                count++;
            }
        }

        System.out.println(count);
    }//
}
