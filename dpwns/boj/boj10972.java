import java.util.*;
import java.io.*;

class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] permutation = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int st = n-1, minIdx = n-1;
        while(st > 0) {
            if(permutation[st] > permutation[st-1]) {   // 맨 뒤에서 부터 오름 차순 찾기 7 9 8 -> 9 8이오름차순의 끝이므로 8과 7을 바꾸고 sort -> 879
                int finalSt = st - 1;
                int min = Arrays.stream(permutation, st, n).filter(p -> p > permutation[finalSt]).min().getAsInt();
                for(int i=st; i<n; i++) {
                    if(min == permutation[i]) {
                        minIdx = i;
                        break;
                    }
                }
                break;
            }
            st--;
        }
        if(st == 0) System.out.println("-1");
        else {
            int tmp = permutation[st-1];
            permutation[st-1] = permutation[minIdx];
            permutation[minIdx] = tmp;
            Arrays.sort(permutation, st, n);

            StringBuilder sb = new StringBuilder();
            Arrays.stream(permutation).forEach(p -> sb.append(p).append(" "));
            System.out.println(sb);
        }

        br.close();
    }
}