import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj6137 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] words = new char[n];
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine().charAt(0);
        }

        System.out.println(findDictionaryFirst(words));
    }

    private static String findDictionaryFirst(char[] words) {
        StringBuilder result = new StringBuilder();
        int left = 0;
        int right = words.length - 1;
        int length = 0;

        while (left <= right) {
            if (words[left] < words[right]) {
                result.append(words[left++]);
            } else if (words[left] > words[right]) {
                result.append(words[right--]);
            } else {
                int l = left;
                int r = right;
                while (l < r) {
                    l++;
                    r--;
                    if (words[l] != words[r]) {
                        break;
                    }
                }

                char c = words[left];
                if (words[l] < words[r]) {
                    while (left <= right && words[left] == c) {
                        result.append(words[left++]);
                        if (++length % 80 == 0) {
                            result.append('\n');
                        }
                    }
                } else {
                    while (left <= right && words[right] == c) {
                        result.append(words[right--]);
                        if (++length % 80 == 0) {
                            result.append('\n');
                        }
                    }
                }
                continue;
            }

            // 80 개행
            if (++length % 80 == 0) {
                result.append('\n');
            }
        }

        return result.toString();
    }

}

