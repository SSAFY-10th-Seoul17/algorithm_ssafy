import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String std = scanner.nextLine();
        int cnt = 0;
        for(int i = 0; i < str.length() - std.length() + 1; i++){
            boolean same = true;
            for(int j = 0; j < std.length(); j++){
                if(str.charAt(i + j) != std.charAt(j)){
                    same = false;
                    break;
                }
            }
            if(same){
                cnt += 1;
                i += std.length() - 1;
            }
        }
        System.out.println(cnt);

        scanner.close();
    }
}