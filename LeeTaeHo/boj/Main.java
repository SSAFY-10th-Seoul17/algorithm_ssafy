import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Loop:
        while(true){
            Stack<Character> stack = new Stack<>();
            char[] line = br.readLine().toCharArray();
            if(line[0] == '.') break;
            for (char c : line) {
                if (c == '(' || c == '[') {
                    stack.push(c);
                } else if (c == ')') {
                    if (stack.isEmpty() || stack.peek() != '(') {
                        sb.append("no\n");
                        continue Loop;
                    } else {
                        stack.pop();
                    }
                } else if (c == ']') {
                    if (stack.isEmpty() || stack.peek() != '[') {
                        sb.append("no\n");
                        continue Loop;
                    } else {
                        stack.pop();
                    }
                }
            }
            if (stack.isEmpty()){
                sb.append("yes\n");
            }else{
                sb.append("no\n");
            }
        }
        System.out.println(sb);
    }
}