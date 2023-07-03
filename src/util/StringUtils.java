package util;

import java.util.regex.Pattern;

public class StringUtils {
    public static boolean containsOnlyNumbers(String input) {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        return pattern.matcher(input).matches();
    }
}
