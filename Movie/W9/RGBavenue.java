package W9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGBavenue {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][]dp = new int[1001][3];

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        dp[1][0] = Integer.parseInt(st.nextToken());
        dp[1][1] = Integer.parseInt(st.nextToken());
        dp[1][2] = Integer.parseInt(st.nextToken());


        for(int i = 2; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dp[i][0] = a + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = b + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = c + Math.min(dp[i-1][1], dp[i-1][0]);

        }

        System.out.println(Math.min(Math.min(dp[N][0],dp[N][1]),dp[N][2]));


    }
}
