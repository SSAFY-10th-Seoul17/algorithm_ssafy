import java.io.*;
import java.util.*;

public class Main {

    static String str;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();

        System.out.println(PPAP() ? "PPAP" : "NP");


    }

    public static boolean PPAP () {
        int pCnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'P') {
                pCnt++;
            }
            else {
                if (pCnt >= 2 && i + 1 < str.length() && str.charAt(i+1) == 'P') {
                    pCnt-=2;
                }
                else {
                    return false;
                }
            }
        }
        if (pCnt == 1) {
            return true;
        }
        return false;
    }
}
