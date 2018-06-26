package cjt.beans;

import java.util.regex.Pattern;
/**
 * created by cjt
 * 2018-06-25
 * 手机号码的正则仅支持目前国内所有的号码，对于未来号码如有扩展请修改正则
 * */
public class PhoneInfoOperator extends InfoOperator {

    public static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$");

    @Override
    public boolean shouldOperator(String input) {
        return PHONE_NUMBER_PATTERN.matcher(input).matches();
    }

    @Override
    public String operator(String input) {
        return input.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
    }
}
