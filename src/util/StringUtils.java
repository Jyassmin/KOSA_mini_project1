package util;

import java.util.Scanner;
import java.util.regex.Pattern;

/*
    String에 관련된 Utility class
 */
public class StringUtils {

    /*
        넘겨받은 string variable이 숫자로만 이루워져있는지 확인하는 메소드
        숫자로만 이루어져 있다면 return true, 아니면 false
     */
    public static boolean containsOnlyNumbers(String input) {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        if (!pattern.matcher(input).matches()) {
            System.out.println("* 잘못된 입력입니다. 숫자를 정확히 입력해주세요.");
            return false;
        }
        return true;
    }

    /*
        넘겨받은 string을 console에 print하고 입력 받은 값을 리턴해주는 메소드
     */
    public static String printAndGetInput(String printMessage) {
        Scanner sc = new Scanner(System.in);
        System.out.print(printMessage);

        return sc.nextLine();
    }
}
