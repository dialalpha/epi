import java.util.List;

public class AdvanceThroughArray {

    /*
       6.4
    */


    /*
    * arr:          [2, 4, 1, 1, 0, 2, 3]
    * index:        [0, 1, 2, 3, 4, 5, 6]
    * max:          [2, 5, 3, 4, 4, 7, 9] ... max we can reach from index
    * */
    public static boolean arrayAdvance(List<Integer> A) {
        return maxCanReach(A, 0, 0);
    }

    private static  boolean maxCanReach(List<Integer> a, int maxRachedSoFar, int index) {
        if (maxRachedSoFar >= a.size() - 1) return true;
        if (index >= a.size()) return false;

        // you can only advance if the value here is gt > 0
        if (a.get(index) > 0) {
            maxRachedSoFar = Math.max(index + a.get(index), maxRachedSoFar);
        }

        return maxCanReach(a, maxRachedSoFar, index + 1);
    }
}
