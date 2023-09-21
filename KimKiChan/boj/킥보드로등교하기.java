package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//27977번
public class 킥보드로등교하기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        //학교까지의 거리 L
        int L = Integer.parseInt(st.nextToken());
        //킥보드 충전소의 개수 N
        int N = Integer.parseInt(st.nextToken());
        //최대 충전소 방문 횟수 K
        int K = Integer.parseInt(st.nextToken());
        // 충전소
        int[] charger = new int[N+2];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= N; i++) {
            charger[i] = Integer.parseInt(st.nextToken());
        }
        charger[0] = 0;
        charger[N+1] = L;
        //입력 끝
        
        sol(charger, K, L);
        
    }// end of main

    private static void sol(int[] charger, int k, int l) {
        int maxDist = 0; // 충전소간의 최대 거리
    	for(int i = 1; i < charger.length; i++) {
        	maxDist = Math.max(maxDist, charger[i] - charger[i-1]);
        }
    	
    	int start = maxDist;
        int end = l;
        int result = 0;
        while(start <= end) {
            int mid = (start + end)/2; // 배터리 용량 
            int count = 0; // 방문 횟수
            int dist = mid; // 거리, 남은 배터리
            int pos = 0; // 출발 충전소
            for(int i = 1; i < charger.length; i++) {
                if(charger[i] - pos > dist) {
                	count++;
                	dist = mid;
                }
                dist -= (charger[i] - pos); // 배터리 감소
                pos = charger[i];
                
                if(count > k) break;
            }
            
            if(count > k) { // 최대횟수보다 많이 방문할 경우 혹은 배터리가 부족하여 다음 충전소로 못 갈 경우, 배터리 용량을 늘린다.
                start = mid+1;
            }else {
            	result = mid;
                end = mid-1;
            }
        }
        
        System.out.println(result);
        
    }
}// end of class