package io.comeandcommue.lib.web.exception;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(GlobalExceptionHandlingConfiguration.class)
public @interface EnableGlobalExceptionHandling {
}
