package util;

import java.util.regex.Pattern;

public class StringUtils {
    public static boolean containsOnlyNumbers(String input) {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        if(!pattern.matcher(input).matches()){
            System.out.println("* 잘못된 입력입니다. 숫자를 정확히 입력해주세요.");
            return false;
        }

        return true;
    }
}
