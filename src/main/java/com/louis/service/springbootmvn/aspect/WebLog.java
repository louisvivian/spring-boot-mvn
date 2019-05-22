package com.louis.service.springbootmvn.aspect;

import java.lang.annotation.*;

/**
 * WebLog
 *
 * @author wangxing
 * @date 2019-5-22
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface WebLog {
    String description() default "";
}
