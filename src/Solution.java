import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * Created by anurags on 9/6/15.
 */
public class Solution {

    private static long numWaysToNRecursive(int N, int[] C, int m) {
        if (N == 0) {
            return 1;
        }

        if (N < 0) {
            return 0;
        }

        if (m <= 0 && N >= 1) {
            return 0;
        }

        return numWaysToNRecursive(N - C[m - 1], C, m) +
                numWaysToNRecursive(N, C, m - 1);
    }

    private static long numWaysToNBottomUp(int N, int[] C) {
        long[][] table = new long[N + 1][C.length];

        for (int i = 0; i < C.length; i++) {
            table[0][i] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < C.length; j++) {
                long withCount = (i - C[j] >= 0) ? table[i - C[j]][j] : 0;
                long withoutCount = j > 0 ? table[i][j - 1] : 0;

                table[i][j] = withCount + withoutCount;
            }
        }

        return table[N][C.length - 1];
    }

    public static void main(String[] args) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            String line1 = br.readLine();
            String line2 = br.readLine();

            String[] NM = line1.split(" ");
            int N = Integer.parseInt(NM[0]);
            int M = Integer.parseInt(NM[1]);
            int[] C = new int[M];
            String[] coinValues = line2.split(" ");

            for (int i = 0; i < C.length; i++) {
                C[i] = Integer.parseInt(coinValues[i]);
            }

            Arrays.sort(C);

            System.out.println(numWaysToNBottomUp(N, C));
            System.out.println(numWaysToNRecursive(N, C, C.length));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}