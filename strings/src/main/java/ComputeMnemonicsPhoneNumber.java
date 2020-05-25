import java.util.ArrayList;
import java.util.List;

public class ComputeMnemonicsPhoneNumber {

    /*
    7.7
    */

    private static String[] numToLetterMap = {"", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

    public static List<String> computeMnemonics(String phoneNumber) {
        StringBuilder sb = new StringBuilder(phoneNumber);
        List<String> results = new ArrayList<>();
        backtrack(phoneNumber, 0, sb, results);
        return results;
    }

    private static void backtrack(String phoneNumber, int i, StringBuilder sb, List<String> results) {
        if (i >= phoneNumber.length()) {
            results.add(sb.toString());
            return;
        }


        int num = Integer.parseInt(String.valueOf(phoneNumber.charAt(i)));
        String letters = numToLetterMap[num];

        for (int j = 0; j < letters.length(); j++) {
            sb.setCharAt(i, letters.charAt(j));
            backtrack(phoneNumber, i+1, sb, results);
        }
    }

}
