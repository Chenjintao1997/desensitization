package cjt.beans;

import java.util.regex.Pattern;

public class RealNameInfoOperator extends InfoOperator {

    public static final Pattern REAL_NAME_PATTERN = Pattern.compile("^([a-zA-Z\\u4e00-\\u9fa5\\·]{1,12})$");
    @Override
    public boolean shouldOperator(String input) {
        return REAL_NAME_PATTERN.matcher(input).matches();
    }

    @Override
    public String operator(String input) {
        if (input.length() <= 4 && !input.contains("·")){
            return input.replaceAll("(\\S)[\\S]+","$1**");
        }else if (input.contains("·")) return input.replaceAll("(\\S+)[·]\\S+","$1·**");
        return null;
    }
}
