package cjt.annotations;

import cjt.beans.InfoOperator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InfoMask {
    //需要脱敏的信息类型对象
    Class<? extends InfoOperator> value();
    //白名单
    String[] whiteListKey() default {};
    //黑名单
    String[] blackListKey() default {};
}
