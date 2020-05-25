import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ComputeRandomPermutation {

    /*
    6.13
    */

    public static List<Integer> computeRandomPermutation(int n) {
        List<Integer> ints = IntStream.range(0, n).boxed().collect(Collectors.toList());
        Random rng = new Random();
        for (int i = n; i > 0; i--) {
            Collections.swap(ints, i-1, rng.nextInt(n));
        }

        return ints;
    }
}

