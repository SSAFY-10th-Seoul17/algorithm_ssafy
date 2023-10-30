import java.util.*;
public class Main {
    public static boolean isPrime(int num){
        if(num == 1) return false;
        for(int i = 2; i * i <= num; i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashSet<Integer> set = new HashSet<Integer>();
        int N = scanner.nextInt();

        for(int i = 2; i < 1000000; i++){
            if(isPrime(i)) set.add(i);
        }
        ArrayList<Integer> arr = new ArrayList<Integer>(set);
        Collections.sort(arr);
        for(int i = 0; i < N; i++){
            int num = scanner.nextInt();
            int cnt = 0;
            for(int j = 0; arr.get(j) < num ; j++){
                if(num - arr.get(j) < arr.get(j)) break;
                if(set.contains(num - arr.get(j))) {
                    cnt += 1;
                }
            }
            System.out.println(cnt);
        }
        scanner.close();
    }
}