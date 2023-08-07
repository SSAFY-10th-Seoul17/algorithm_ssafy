import java.io.*;

public class Boj7490 {
    static StringBuilder result = new StringBuilder();
    static int[] arr;
    static int top;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            arr = new int[n];
            dfs(new int[n - 1], 0);
            result.append("\n");
        }

        System.out.print(result);
    }

    static void dfs(int[] op, int depth) {
        if (depth >= op.length) {
            if (getSum(op) == 0) {
                addResultString(op);
            }
            return;
        }

        for (int i = 0; i < operators.length; i++) {
            op[depth] = i;
            dfs(op, depth + 1);
        }

    }

    private static int getSum(int[] op) {
        top = -1;
        arr[++top] = 1;

        int number = 2;

        for (int operator : op) {
            if (operator == 1) {
                arr[++top] = number;
            } else if (operator == 2) {
                arr[++top] = -number;
            } else {
                arr[top] *= 10;
                arr[top] = arr[top] + (arr[top] >= 0 ? number : -number);
            }
            number++;
        }

        int sum = 0;
        for (int i = 0; i <= top; i++) {
            sum += arr[i];
        }
        return sum;
    }

    static final char[] operators = {' ', '+', '-'};

    private static void addResultString(int[] op) {
        int number = 1;
        result.append(number++);

        for (int operator : op) {
            result.append(operators[operator]).append(number++);
        }
        result.append("\n");
    }

}

