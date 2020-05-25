public class SpreadsheetColumnEncoding {

    /*
    7.3
    */

    /*
    * eg:   given A -> 1
    *       given D -> 4
    *       given AA -> 27
    * */
    public static int decodeSpreadsheetColumn(String code) {
        int result = 0;
        int pow = 0;
        for (int i = code.length() - 1; i >= 0; i--) {
            result += (int)Math.pow(26, pow) * getLetterIntValue(code.charAt(i));
            pow++;
        }

        return result;
    }

    private static int getLetterIntValue(char c) {
        return Character.getNumericValue(c) - 9; // because the value of 'A' is 10, map it 1
    }


}
