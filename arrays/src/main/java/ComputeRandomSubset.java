import java.util.List;
import java.util.stream.Collectors;

public class ComputeRandomSubset {

    /*
    6.14
    */

    public static List<Integer> randomSubset(int n, int k) {

        List<Integer> integers = ComputeRandomPermutation.computeRandomPermutation(n);
        return integers.stream().limit(k).collect(Collectors.toList());
    }
}
