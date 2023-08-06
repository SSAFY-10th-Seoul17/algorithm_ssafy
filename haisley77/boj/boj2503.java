import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj2503 {
    
    static ArrayList<Integer> arr;    // 순열 저장 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new ArrayList<Integer>();
        permutation();        // 가능한 모든 세자리수 저장

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int num = Integer.parseInt(st.nextToken());
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());
            check(num,strike,ball);
        }

        System.out.println(arr.size());

    }
    
    private static void check(int num, int strike, int ball) {
        
        int tmps = 0;
        int tmpb = 0;
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        for (int i = 0; i < arr.size(); i++) {
            tmps = 0;
            tmpb = 0;
            String s1 = String.valueOf(num);
            String s2 = String.valueOf(arr.get(i));

            for (int j = 0; j < 3; j++) {
                if (s2.contains(s1.charAt(j)+"") && s2.indexOf(s1.charAt(j)+"") == j) {
                    tmps++;
                }
                if (s2.contains(s1.charAt(j)+"") && s2.indexOf(s1.charAt(j)+"") != j) {
                    tmpb++;
                }
            }
            if (strike != tmps || ball != tmpb) {
                tmp.add(arr.get(i));
            }
        }
        
        for (int i = 0; i < tmp.size(); i++) {
            arr.remove(tmp.get(i));
        }
        
    }
    
    
    private static void permutation() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (j != i) {
                    for (int k = 1; k <= 9; k++){
                        if (i != k && j != k) {
                            arr.add(i * 100 + j * 10 + k);
                        }
                    }
                }
            }
        }
    }

}
