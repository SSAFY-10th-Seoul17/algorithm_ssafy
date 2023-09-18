import java.io.*;

public class Boj16472 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(getMaxCatLang(Integer.parseInt(br.readLine()), br.readLine()));
    }

    private static int getMaxCatLang(int n, String catLang) {
        int[] counts = new int['z' - 'a' + 1];
        int left = 0;
        int maxCount = 0;
        int types = 0;

        for (int right = 0; right < catLang.length(); right++) {
            char c = catLang.charAt(right);

            if (++counts[c - 'a'] == 1 && ++types > n) {
                while (--counts[catLang.charAt(left++) - 'a'] > 0);
                types--;
            }
            maxCount = Math.max(maxCount, right - left + 1);
        }

        return maxCount;
    }

}

