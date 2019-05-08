package com.ad.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * created by Mr.F on 2019/4/30
 */
@Target({ElementType.TYPE,ElementType.METHOD})//让这个注解能标识在类和方法上面
@Retention(RetentionPolicy.RUNTIME)//代表是系统运行时
public @interface IgnoreResponseAdvice {
}
