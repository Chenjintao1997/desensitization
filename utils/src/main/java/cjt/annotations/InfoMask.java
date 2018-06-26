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
    Class<? extends InfoOperator> value();
    String[] whiteListKey() default {};
    String[] blackListKey() default {};
}
