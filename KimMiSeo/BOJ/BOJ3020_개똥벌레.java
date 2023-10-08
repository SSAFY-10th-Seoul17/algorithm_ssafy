import java.io.*;
import java.util.*;

/**
 * 높이에 따라 만나는 석순, 종유석의 개수를 누적합으로 구해서 최솟값을 찾는다.
 * 최솟값을 구하고, 그에 대한 개수를 구하는 코드를,,, 실수해서 계속 틀렸다. 실수를 조심하자!!
 */
public class BOJ3020_개똥벌레 {
    static int N,H, minresult, cnt;
    static int[] suk , jong;
    static int[] sumsuk, sumjong;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        suk = new int[H+1];
        jong = new int[H+1];

        sumsuk = new int[H+1];
        sumjong = new int[H+1];
        minresult = Integer.MAX_VALUE;
        cnt = 0;

        for (int i=0; i<N; i++){
            int temp = Integer.parseInt(br.readLine());
            if (i%2 == 0) { // 홀수일때 - 석순일때
                suk[temp] += 1;
            }else{ // 짝수일때 - 종유석일때
                jong[H - temp + 1] += 1;
            }
        }

        // 석순- 위에서부터
        sumsuk[H] = suk[H];
        for (int i=H-1; i>0; i--){
            if (suk[i] > 0) { // 석순이 존재하면
                sumsuk[i] = sumsuk[i + 1] + suk[i];
            }else {
                sumsuk[i] = sumsuk[i + 1];
            }
        }

        // 종유석 - 아래서부터
        for (int i=1; i<=H; i++){
            if (jong[i] > 0){
                sumjong[i] = sumjong[i - 1] + jong[i];
            }else{
                sumjong[i] = sumjong[i - 1];
            }
        }

//        System.out.println(Arrays.toString(sumsuk));
//        System.out.println(Arrays.toString(sumjong));

        minresult = Integer.MAX_VALUE;

        for (int i=1; i<=H; i++){
            int temp = sumsuk[i] + sumjong[i];
            if ( minresult > temp ){
                minresult = temp;
                cnt = 1;
            } else if (minresult == temp){
                cnt ++;
            }
        }
        System.out.println(minresult+" "+cnt);
    }
}
