import java.util.List;

public class BuySellStockOnce {

    /*
       6.6
    */

    /*
    * prices:       [310, 315, 275, 295, 260, 270, 290, 230, 255, 250]
    * min:          [310, 310, 275, 275, 260, 260, 260, 230, 250, 230] min seen from left
    * max:          [315, 315, 295, 295, 290, 290, 290, 255, 255, 250] max seen from right
    * profit:       max[i] - min[i]
    * */
    public static int buySellStockOnce(List<Integer> A) {
        //return solveWithHighSpace(A);
        return solveOptimal(A);
    }

    private static int solveOptimal(List<Integer> A) {
        // iter from left, keep track of min so far
        // update max profit at each location
        int len = A.size();
        int minSofar = A.get(0);
        int profit = 0;
        for (int i = 1; i <len; i++) {
            minSofar = Math.min(minSofar, A.get(i));
            profit = Math.max(profit, A.get(i) - minSofar);
        }
        return profit;
    }

    // linear time + linear space
    private static int solveWithHighSpace(List<Integer> A) {
        int len = A.size();
        int[] min = new int[len]; // min from left
        int[] max = new int[len]; // max from right

        min[0] = A.get(0);
        for (int i = 1; i < min.length; i++) {
            min[i] = Math.min(A.get(i), min[i-1]);
        }

        max[len - 1] = A.get(len -1);
        for (int i = len-2; i >= 0; i--) {
            max[i] = Math.max(A.get(i), max[i+1]);
        }

        int profit = 0;
        for (int i = 0; i < len; i++) {
            profit = Math.max(profit, max[i] - min[i]);
        }
        return profit;
    }
}
