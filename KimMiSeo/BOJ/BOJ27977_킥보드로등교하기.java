import java.io.*;
import java.util.*;
public class BOJ27977_킥보드로등교하기 {
    static int L,N,K; // 학교까지 거리, 충전소 개수, 최대 충전소 방문횟수
    static int[] chargeLocation;
    public static void main(String[] args) throws Exception{
        // 킥보드 - 1을 소모해 1만큼 이동 가능, 소진되기 전에 충전해야함
        // 0, L 위치 , n개의 충전소가 순서대로 있음, 최대 k 번 충전소에 방문하기로 함
        // 구매할 배터리 용량 구하기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        chargeLocation = new int[N+2];
        chargeLocation[0] = 0;
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++){
            chargeLocation[i] = Integer.parseInt(st.nextToken());
        }
        chargeLocation[N+1] = L;

        int start = 1;
        int end = L;
        int result = 0;

        while (start <= end) {
            int mid = (start + end) / 2;
            //System.out.println("mid "+mid);
            int chargeCnt = 0;
            int curLocation = 0;
            int leftFuel = mid;

            for (int i=1; i<chargeLocation.length; i++) {
                if (chargeLocation[i] - curLocation > leftFuel) {
                    chargeCnt++;
                    leftFuel = mid;
                }
                leftFuel -= (chargeLocation[i] - curLocation);
                curLocation = chargeLocation[i];

                if (leftFuel < 0){
                    break;
                }

                if (chargeCnt > K){
                    break;
                }
            }

            if (chargeCnt > K || leftFuel < 0) {
                start = mid + 1;
            }else{
                result = mid;
                end = mid-1;
            }

        }
        System.out.println(result);
    }
}
