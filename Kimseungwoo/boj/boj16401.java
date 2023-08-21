package toyproject.somedaybucket.myAlgo.boj;

import java.io.*;
import java.util.*;

public class boj16401 {

    static int M; // 조카의 수
    static int N; // 과자의 수
    static int max = Integer.MIN_VALUE; // 최대 과자의 길이

    static int[] cookie;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        cookie = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        int max_length = -1;
        int min_length = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++){
            cookie[i] = Integer.parseInt(st.nextToken());
            if(max_length < cookie[i]){
                max_length = cookie[i];
            }
            if(min_length > cookie[i]){
                min_length = cookie[i];
            }
        }

        binarySearch(1, max_length); // 만약 현재 저장된 길이보다 작게 자르는 것이라면 재귀를 그만함
        if(max == Integer.MIN_VALUE){
            System.out.println(0);
        } else {
            System.out.println(max);
        }

    } // 81퍼에서 틀림!

    private static void binarySearch(int start, int end){
        if(start > end) {
            return;
        }
        int divNum = 0;
        int mid = (start+end)/2;
        for(int i = 0; i<N; i++){
            divNum += cookie[i]/mid;
        }
        if(divNum >= M){ // 충분히 자르고도 남는 경우
            if(mid > max) {
                max = mid;
            }
            binarySearch(mid+1, end);
        } else { // 자르기가 부족한 경우
            binarySearch(start, mid-1);
        }
    }
}
