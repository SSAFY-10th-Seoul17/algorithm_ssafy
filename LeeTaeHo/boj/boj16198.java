import java.io.*;
import java.util.*;
public class boj16198 {
    static int N, max;
    static int[] energy;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        energy = new int[N];
        visited = new boolean[N];
        max = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            energy[i] = Integer.parseInt(st.nextToken());
        }

        recursive(N, 0);

        System.out.println(max);
    }

    public static void recursive(int depth, int result){
        if(depth == 2){
            max = Math.max(max, result);
            return;
        }

        for (int i = 1; i < N - 1; i++) {
            if(!visited[i]){
                visited[i] = true;

                int left = i - 1;
                int right =  i + 1;

                for (int j = i - 1; j >= 0; j--){
                    left = j;
                    if(!visited[j]) break;
                }

                for (int j = i + 1; j < energy.length; j++){
                    right = j;
                    if(!visited[j]) break;
                }

                recursive(depth - 1, result + energy[left] * energy[right]);
                visited[i] = false;
            }
        }
    }
}
