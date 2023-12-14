package com.zhangjian.aop;

import java.lang.annotation.*;

@Retention( RetentionPolicy.RUNTIME)
@Target( ElementType.METHOD)
public @interface PrintLog {
}
