package cjt.beans;

import javax.print.DocFlavor;
import java.util.regex.Pattern;

public class IdCardInfoOperator extends InfoOperator {
    public static final Pattern CITIZEN_ID_15_NUMBER_PATTERN = Pattern.compile("^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$");

    public static final Pattern CITIZEN_ID_18_NUMBER_PATTERN = Pattern.compile("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$");

    @Override
    public boolean shouldOperator(String input) {
        if (CITIZEN_ID_15_NUMBER_PATTERN.matcher(input).matches() || CITIZEN_ID_18_NUMBER_PATTERN.matcher(input).matches()) return true;
        return false;
    }

    @Override
    public String operator(String input) {
        if (input.length() == 15){
            return input.replaceAll("(\\d{4})\\d{8}(\\w{3})","$1******$2");
        } else if (input.length() == 18){
            return input.replaceAll("(\\d{4})\\d{10}(\\w{4})","$1******$2");
        }
        return null;
    }
}
