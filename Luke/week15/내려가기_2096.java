import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 내려가기_2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] dpMax = new int[4][N + 1];
        int[][] dpMin = new int[4][N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        dpMax[1][1] = dpMin[1][1] = a;
        dpMax[2][1] = dpMin[2][1] = b;
        dpMax[3][1] = dpMin[3][1] = c;

        for (int i = 2; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            dpMax[1][i] = getMax(dpMax[1][i - 1], dpMax[2][i - 1], 0) + a;
            dpMax[2][i] = getMax(dpMax[1][i - 1], dpMax[2][i - 1], dpMax[3][i - 1]) + b;
            dpMax[3][i] = getMax(dpMax[2][i - 1], dpMax[3][i - 1], 0) + c;

            dpMin[1][i] = Math.min(dpMin[1][i - 1], dpMin[2][i - 1]) + a;
            dpMin[2][i] = getMin(dpMin[1][i - 1], dpMin[2][i - 1], dpMin[3][i - 1]) + b;
            dpMin[3][i] = Math.min(dpMin[2][i - 1], dpMin[3][i - 1]) + c;
        }

        int max = getMax(dpMax[1][N], dpMax[2][N], dpMax[3][N]);
        int min = getMin(dpMin[1][N], dpMin[2][N], dpMin[3][N]);

        System.out.println(max + " " + min);
    }

    private static int getMax(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    private static int getMin(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}
