- solve2로 해결하고 몇 분 뒤 해당 로직의 한계가 있다는 것을 깨달았다.
- 괄호의 종류가 `()`뿐만아니라 `{}, []`도 있다면 해결할 수 없다는 것이다.

- 그렇다면 어떻게 할 수 있을까?
- 처음에는 Enum이 떠올랐다.
    - `(), {}, []` 주어진 타입 중에 괄호 검사를 만족? => Enum으로 타입 제한하고 괄호 짝이 맞는지 검사
    - 할 수 있을 것같았다..
    - `Enum`하니까 아래와 같은 모양이 떠올랐다.

```java
public enum Parenthesis {
    ((')'),{('}'), [("]");
    }
```

- 문법적으로 에러다!

- 모양을 보니 key-value -> hashMap()이 떠올랐다.

> 코드 설명
> - 아래 element를 hashMap에 저장한다.
>   - key: value -> '(' : ')' / '{' : '}' / '[' : ']'
> - input으로 주어지는 괄호식을 순회하면서 여는 괄호는 stack에 넣는다.
> - 닫는 괄호가 나왔을 떄
>   1. stack이 비어있으면 `false`를 리턴한다.
>   2. 현재 문자(now)와 stack의 가장 위 문자(map.get(stack.pollLast()))와 다르면 `false`를 리턴한다.
>   3. input 문자열이 종료되었을 때 stack이 비어있지 않으면 `false`를 리턴한다.
> - 위 모든 조건식을 통과하는 경우 true를 리턴한다.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;

public class Main {
    static String solve(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');

        ArrayDeque<Character> stack = new ArrayDeque<>();

        // O(N)
        for (int i = 0; i < s.length(); i++) {
            char now = s.charAt(i);   // O(1)

            if (map.containsKey(now)) { // map의 containsKey 시간 복잡도: O(1)
                stack.addLast(now);
            } else {
                if (stack.isEmpty()) {
                    return "NO";
                } else {
                    if (now != map.get(stack.pollLast())) {
                        return "NO";
                    }
                }
            }
        }
        if (!stack.isEmpty()) {
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
```
- 괄호 쌍으로 어떤 문자가 오더라도 map에 추가하면 동일하게 가능할 것이다.
👍