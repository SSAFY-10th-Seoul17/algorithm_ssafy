import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int num[] = new int[M];
        
        int left = 1;
        int right = 0;
        
        for(int i=0; i<M; i++){
            num[i] = Integer.parseInt(br.readLine());
            right = Math.max(right, num[i]);
        }
        
        int ans = 0;
        while(left <= right){
            int mid = (left+right)/2;
            int sum = 0;
        
            for(int i=0; i<M; i++){
            	sum += num[i]/mid;
                if(num[i] % mid != 0){
                    sum ++;
                }
            }
            
            if(sum > N){
                left = mid+1;
            }else{
                right = mid-1;
                ans = mid;
            }
        }
        System.out.println(ans);
    }
}
