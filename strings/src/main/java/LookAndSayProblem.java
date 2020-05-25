import java.util.HashMap;
import java.util.Map;

public class LookAndSayProblem {

    /*
    7.8
    */

    public static String lookAndSay(int n) {
        Map<String, String> cache = new HashMap<>();
        String s = "1";
        for (int i = 1; i < n; i++) {
            s = generate(s);
        }

        return s;
    }

    private static String generate(String seq) {
        char c = seq.charAt(0);
        int count = 1;

        StringBuilder result = new StringBuilder();
        for (int i = 1; i < seq.length(); i++) {
            if (seq.charAt(i) == c) {
                count++;
            } else {
                // append aggregation
                result.append(aggregate(c, count));

                // reset
                c = seq.charAt(i);
                count = 1;
            }
        }

        // must call one last time because if condition won't execute when i == seq.length and we have accumulated a few chars
        result.append(aggregate(c, count));
        return result.toString();
    }

    private static String aggregate(char c, int count) {
        return "" + count + c;
    }

}
