import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * solve1. 가장 흔한 스택 자료구조를 통한 괄호 검사가 있다.
 * solve2. 누적합으로 가능? input의 길이만큼 int 배열 생성
 * => i번째 element는 input에서 i번째까지 "("의 개수 - ")"의 개수
 * => 모든 element가 0 이상이고 마지막 element가 0이면 YES 아니면 NO
 * solve3. hashMap()
 */

public class Main {

    public static String solve(String input) {

        int count = 0;
        // ((())) => 1 -> 2 -> 3 -> 2 -> 1 -> 0 ==> "YES
        // (()))( => 1 -> 2 -> 1 -> 0 -> -1 ==> "NO"
        // (())( => 1 -> 2 -> 1 -> 0 -> 1 ==> "NO"
        // ))(( => -1 ==> "NO"
        for (char c : input.toCharArray()) {
            if (c == '(') {
                count++;
            }
            if (c == ')') {
                count--;
                if (count < 0) {
                    return "NO";
                }
            }
        }

        if (count != 0) {
            return "NO";
        }

        return "YES";
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String input = br.readLine();

            System.out.println(solve(input));
        }
    }
}