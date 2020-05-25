import java.util.ArrayList;
import java.util.List;

public class ComputePascalsTriangle {

    /*
    6.19
    */

    public static List<List<Integer>> generatePascalTriangle(int n) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            List<Integer> row = new ArrayList<>(i);
            for (int k = 0; k < i; k++) {
                if (k == 0 || k == i -1) {
                    row.add(1);
                } else {
                    List<Integer> rowAbove = result.get(i - 2);
                    row.add(rowAbove.get(k) + rowAbove.get(k-1));
                }
            }

            result.add(row);
        }
        return result;
    }
}
