import java.util.*;
import java.io.*;
public class BOJ2467_용액 {
    static int N;
    static int[] nums;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i=0; i<N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        int[] result = solve();
        System.out.println(result[0]+" "+result[1]);
    }

    private static int[] solve(){
        int minnum = nums[0];
        int maxnum = nums[N-1];

        // 모두 양수로 이루어져있으면 -> 제일 작은 2개
        if (minnum >= 0 && maxnum >= 0){
            return new int[] {nums[0], nums[1]};
        }
        // 모두 음수로 이루어져있으면 -> 제일 큰거 2개
        if (minnum < 0 && maxnum < 0){
            return new int[] {nums[N-2],nums[N-1]};
        }

        // 혼합이면 -> 양끝투포인터
        // 더했을 때 0이면 바로 리턴
        // 양수 + 음수 : 더했을 때 양수면 - start를 옮긴다 더 커지면 그만
        // 양수 + 음수 : 더했을 때 음수면 - end를 옮긴다 더커지면 그만
        int start = 0;
        int end = N-1;
        int min = Integer.MAX_VALUE;
        int[] minresult = new int[2];
        while(start < end ){
            int sum = nums[start] + nums[end];
            if ( sum == 0 ){
                return new int[] {nums[start], nums[end]};
            }

            if ( sum > 0 ){
                if ( min > sum ){
                    min = sum;
                    minresult = new int[] {nums[start], nums[end]};
                }
                end--;
            }

            if (sum < 0){
                if (min > Math.abs(sum)){
                    min = Math.abs(sum);
                    minresult = new int[] {nums[start], nums[end]};
                }
                start++;
            }

        }
        return minresult;
    }
}
