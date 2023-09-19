package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//2096번
public class 내려가기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int left = Integer.parseInt(st.nextToken());
        int middle = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());
        
        int[] maxScore = new int[] {left, middle, right};
        int[] minScore = new int[] {left, middle, right};
        int[] temp = new int[3];
        
        for(int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            left = Integer.parseInt(st.nextToken());
            middle = Integer.parseInt(st.nextToken());
            right = Integer.parseInt(st.nextToken());
            
            temp = Arrays.copyOf(maxScore, 3);
            maxScore[0] = Math.max(temp[0], temp[1]) + left;
            maxScore[1] = Math.max(temp[0], Math.max(temp[1], temp[2])) + middle;
            maxScore[2] = Math.max(temp[1], temp[2]) + right;
            
            temp = Arrays.copyOf(minScore, 3);
            minScore[0] = Math.min(temp[0], temp[1]) + left;
            minScore[1] = Math.min(temp[0], Math.min(temp[1], temp[2])) + middle;
            minScore[2] = Math.min(temp[1], temp[2]) + right;
            
        }
        
        int max = Math.max(maxScore[0], Math.max(maxScore[1], maxScore[2]));
        int min = Math.min(minScore[0], Math.min(minScore[1], minScore[2]));
        
        System.out.println(max + " " + min);
        
    }
}