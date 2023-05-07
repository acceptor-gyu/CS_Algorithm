import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AtoB_16953 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int cnt = 1;

        while (A != B) {

            if (A > B) {
                break;
            }

            if (B % 10 == 1) {
                B = B / 10;
                cnt++;
                continue;
            }

            if (B % 2 == 0) {
                B = B / 2;
                cnt++;
                continue;
            } else {
                break;
            }
        }

        if (A == B) {
            System.out.println(cnt);
        } else {
            System.out.println(-1);
        }
    }
}
