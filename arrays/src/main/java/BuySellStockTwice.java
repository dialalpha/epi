import java.util.Arrays;
import java.util.List;

public class BuySellStockTwice {

    /*
    6.7
    */

    /*
            sol: split the prices array into 2 chunks.
                chunk of first buy/sell, and and chunk of second buy/sell
                maximize each
                see: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/

    * prices:       [310, 315, 275, 295, 260, 270, 290, 230, 255, 250]
    * min_l:        [310, 310, 275, 275, 260, 260, 260, 230, 230, 230] min seen from left
    * pr_h_l:       [000, 005, 000, 020, 000, 010, 030, 000, 025, 020] profit from left if we sell at i
    * Forward:      [000, 005, 005, 020, 020, 020, 030, 030, 030, 030] max profit from [0 ... i]

    * prices:       [310, 315, 275, 295, 260, 270, 290, 230, 255, 250]
    * max_r:        [315, 315, 295, 295, 290, 290, 290, 255, 255, 250] max seen from right
    * pr_h_r:       [005, 000, 025, 000, 030, 020, 000, 025, 000, 000] max profit if we buy at i
    * Back:         [030, 030, 030, 030, 030, 020, 025, 025, 000, 000] max profit from [i ... n-1]
    *
    * compare:
    *   forward:    [000, 005, 005, 020, 020, 020, 030, 030, 030, 030]
    *   backward:   [030, 030, 030, 030, 030, 020, 025, 025, 000, 000]
    *
    *   profit at i = forward[i] + backward[i+1]
    *   w
    */

    public static int buySellStockTwice(List<Integer> A) {
        return solveWithHighSpace(A);
    }


    private static int solveWithHighSpace(List<Integer> A) {
        int profit = 0;
        int len = A.size();

        int[] profit_left = new int[len];
        Arrays.fill(profit_left, 0);
        int min = A.get(0);
        for (int i = 1; i < len; i++) {
            min = Math.min(A.get(i), min);
            profit_left[i] = Math.max(A.get(i) - min, profit_left[i-1]);
        }


        int[] profit_right = new int[len];
        Arrays.fill(profit_right, 0);
        int max = A.get(len -1);
        for (int i = len-2; i>=0; i--) {
            max = Math.max(max, A.get(i));
            profit_right[i] = Math.max(max - A.get(i), profit_right[i+1]);
        }

        for (int i = 0; i < len; i++) {
            if (i == len -1) { // special case of end of profit left array, so we don't go out of bounds of right array
                profit = Math.max(profit, profit_left[i]);
            } else {
                profit = Math.max(profit_left[i] + profit_right[i+1], profit);
            }
        }

        return profit;
    }

}
