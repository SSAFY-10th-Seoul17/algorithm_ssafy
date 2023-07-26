import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj7490 {
    static int case_num = 0;
    static int N[];
    public static void main(String[] args) {
        init();
        for (int i = 0; i < case_num; i++) {
            bfs(N[i]);
            System.out.println();
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

    public static int calculate(String trace) {
        String stack = "";
        int sum = 0;
        int p = 0;
        int buho = 1;
        while (p<trace.length()) {
            char temp = trace.charAt(p++);
            if (temp == '+' || temp == '-') {
                sum += Integer.parseInt(stack.replace(" ","")) * buho;
                buho = (temp == '+') ? 1 : -1;
                stack = "";
            }
            else{
                stack += temp;
            }
        }
        return sum + Integer.parseInt(stack.replace(" ","")) * buho;
    }
    public static void bfs(int N){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(1, "1"));
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            int level = temp.level+1;
            String trace = temp.trace;
            if (level == N+1) {
                if(calculate(trace) == 0)
                    System.out.println(trace);
                continue;
            }
            String[] adj_trace = {trace+" "+level, trace+"+"+level, trace+"-"+level};
            for (int i = 0; i < 3; i++) {
                queue.add(new Node( level, adj_trace[i]));
            }

        }
    }
}
class Node{
    int level;
    String trace;
    Node(int level, String trace) {
        this.level = level;
        this.trace = trace;
    }
}
