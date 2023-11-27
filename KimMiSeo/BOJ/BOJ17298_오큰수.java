import java.util.*;
import java.io.*;

/**
 * 반드시 순서대로 오큰수를 구해야 한다는 생각을 버리면 좋을 것 같다는 생각을 했습니다.
 * 숫자가 크고, n으로 끝내야 하는 경우라면 스택을 생각해보면 좋을 것 같습니다.
 */
public class BOJ17298_오큰수 {
    static int N;
    static int[] answers;
    static Stack<int[]> stack = new Stack<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        answers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i=0; i<N; i++){
            int cur = Integer.parseInt(st.nextToken());

            if (stack.isEmpty()){ // 스택이 비어있으면
                stack.push(new int[] {i, cur}); // 인덱스, 값, 정답
            }
            else{ // 스택이 차있으면
                while(!stack.isEmpty() && stack.peek()[1] < cur){
                    int[] small = stack.pop();
                    answers[small[0]] = cur; // 오큰수
                }
                stack.push(new int[]{i,cur});
            }
        }

        while(!stack.isEmpty()){
            int[] pops = stack.pop();
            answers[pops[0]] = -1;
        }
        for(int i=0; i<N; i++){
            sb.append(answers[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}
