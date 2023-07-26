import java.util.*;

public class boj17103 {
    static int case_num = 0;
    static int N[];
    static int max_num = 1000000;
    static List<Integer> sosu = new ArrayList<>();
    public static void main(String[] args) {
        init();
        findSosu();
        for (int i = 0; i < case_num; i++) {
            System.out.println(solution(N[i]));
        }
    }
    public static void init(){
        Scanner sc = new Scanner(System.in);
        case_num = sc.nextInt();
        N = new int[case_num];
        for (int i = 0; i < case_num; i++) {
            N[i] = sc.nextInt();
        }
    }

    public static void findSosu() {
        boolean[] visited = new boolean[max_num+1];
        for (int i = 2; i <= Math.sqrt(max_num); i++) {
            if (!visited[i]) {
                int iter = i;
                int temp = i*2;
                while (temp <= max_num) {
                    visited[temp] = true;
                    temp += iter;
                }
            }
        }
        for (int i = 2; i <= max_num; i++) {
            if (!visited[i]) {
                sosu.add(i);
            }
        }
    }

    public static int solution(int N) {
        int i = 0, j = 0;
        int answer = 0;
        while (true) {
            if (sosu.get(j++) >= N) {
                j = j - 2;
                break;
            }
        }
        while (i <= j) {
            int sum = sosu.get(i) + sosu.get(j);
            if (sum == N) {
                answer++;
                i++;
            } else if (sum > N) {
                j--;
            } else {
                i++;
            }
        }
        return answer;
    }

}

