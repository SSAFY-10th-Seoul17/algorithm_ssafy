import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Integer[] permutation = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        int st = n-1, maxIdx = n-1;
        while(st > 0) {
            if(permutation[st] < permutation[st-1]) {
                // 1 2 5 3 4 -> 이전 순열은 1 2 4 5 3 맨 뒤에서 부터 봤을 때 오름차순이 되는 순간 5보다 작으면서 최댓값과 5를 바꾸고 내림차순 정렬
                int max = Integer.MIN_VALUE;
                for (int i = st; i < n; i++) {
                    if (permutation[i] < permutation[st-1] && permutation[i] > max) {
                        max = permutation[i];
                        maxIdx = i;
                    }
                }
                break;
            }
            st--;
        }
        if(st == 0) System.out.println("-1");
        else {
            int tmp = permutation[st-1];
            permutation[st-1] = permutation[maxIdx];
            permutation[maxIdx] = tmp;
            Arrays.sort(permutation, st, n, (p1, p2) -> p2 - p1);
            StringBuilder sb = new StringBuilder();
            Arrays.stream(permutation).forEach(p -> sb.append(p).append(" "));
            System.out.println(sb);
        }
        br.close();
    }
}