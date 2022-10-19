package com.stussy.stussyclone20220930heeseung.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD}) //ElementType.TYPE = 클래스이다 또는, METHOD = 메소드이다.
public @interface ValidAspect {
}
