import java.util.*;
import java.io.*;
public class BOJ3151_합이0 {
    static int N;
    static long result;
    static int[] numbers;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        } // end

        result = 0;
        Arrays.sort(numbers);
        //System.out.println(numbers);

        for (int i=0; i<N; i++){
            if (numbers[i] <= 0){
                find(i);
            }
        }
        System.out.println(result);
    }

    private static void find(int cur){
        int start = cur+1; // 시작
        int end = N-1; // 끝

        while( start < end ){
            int s = 1;
            int e = 1;
            int sum = numbers[cur] + numbers[start] + numbers[end];
            if ( sum == 0 ){ // 0을 만들 수 있다면
                if (numbers[start] == numbers[end]){ // 같은 숫자를 더해서 0이 만들어졌다면
                    result += comb(end-start+1); // nC2
                    break;
                }

                // start가 중복 수일 때
                while ( numbers[start+1] == numbers[start] ){
                    s++;
                    start++;
                }
                // end가 중복수일때
                while( numbers[end-1] == numbers[end]){
                    e++;
                    end--;
                }
                result +=  s * e;
            }

            if ( sum > 0 ){ // 크면 -> 줄이기
                end--;
            }
            else { // 작으면 -> 키우기
                start++;
            }
        }
    }

    private static int comb(int n){
        return n * (n-1) / 2;
    }

}
