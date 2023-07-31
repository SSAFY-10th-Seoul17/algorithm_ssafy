import java.util.*;

public class boj17484 {
    static Scanner sc = new Scanner(System.in);
    static int min_energy;
    static int N, M;
    static int[][][] dp;
    static int[][] arr;
    static int[] temp = {-1,0,1};

    public static void main(String[] args) {
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        
        dp = new int[N][M][3];
        int px, py;
        for (int i = 0; i < M; i++) {
        	for (int j = 0; j < 3; j++) {
        		dp[0][i][j] = 0;
        	}
        }
        
        
        for (int i = 1; i < N; i++) {
        	for (int j = 0; j < M; j++) {
        		for (int k = 0; k < 3; k++) {
        			px = j + temp[k];	// 이전 위치 (x)
        			py = i - 1;			// 이전 위치 (y)
        			
        			if (px == 0 && py >= 0 && py < N) {
        				if (k == 0) {
            				dp[i][j][k] = Math.min(dp[py][px][1], dp[py][px][2]) + arr[py][px];
            			} else if (k == 1) {
            				dp[i][j][k] = dp[py][px][2] + arr[py][px];
            			} else {
            				dp[i][j][k] = dp[py][px][1] + arr[py][px];
            			}

        			} 
        			if (px == (M - 1) && py >= 0 && py < N) {
        				if (k == 0) {
            				dp[i][j][k] = dp[py][px][2] + arr[py][px];
            			} else if (k == 1) {
            				dp[i][j][k] = dp[py][px][0] + arr[py][px];
            			} else {
            				dp[i][j][k] = Math.min(dp[py][px][0], dp[py][px][1]) + arr[py][px];
            			}
        			}
        			
        			if (px >= 1 && px < (M - 1) && py >= 0 && py < N) {   				
        				if (k == 0) {
            				dp[i][j][k] = Math.min(dp[py][px][1], dp[py][px][2]) + arr[py][px];
            			} else if (k == 1) {
            				dp[i][j][k] = Math.min(dp[py][px][0], dp[py][px][2]) + arr[py][px];
            			} else {
            				dp[i][j][k] = Math.min(dp[py][px][0], dp[py][px][1]) + arr[py][px];
            			}
        			}
        		}
        	}
        }
        
        
        int min_energy = Integer.MAX_VALUE;
        
        for (int i = 0; i < M; i++) {
        	for (int k = 0; k < 3; k++) {
        		if (dp[N-1][i][k] == 0) {
        			continue;
        		}
        		if (min_energy > (dp[N-1][i][k] + arr[N-1][i])) {
        			min_energy = dp[N-1][i][k] + arr[N-1][i];
        		}
        	}
        }
        
        System.out.println(min_energy);
        

    }

}
