package com.philippe.app.util;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import java.lang.reflect.Method;

public class MockMvcControllerAdviceHelper extends ExceptionHandlerExceptionResolver {

    private final Class exceptionHandlerClass;

    private static final String ERROR_MSG = "Unable to instantiate exception handler %s";

    /**
     * MockMvcControllerAdviceHelper constructor
     * @param exceptionHandlerClass Exception Handler Class
     */
    public MockMvcControllerAdviceHelper(Class exceptionHandlerClass) {
        super();
        getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        getMessageConverters().add(new Jaxb2RootElementHttpMessageConverter());
        this.exceptionHandlerClass = exceptionHandlerClass;
        afterPropertiesSet();
    }

    /**
     * Default MockMvcControllerAdviceHelper Constructor
     * @param exceptionHandlerClass Exception Handler Class
     * @return MockMvcControllerAdviceHelper object
     */
    public static MockMvcControllerAdviceHelper mockAdviceFor(Class exceptionHandlerClass) {
        return new MockMvcControllerAdviceHelper(exceptionHandlerClass);
    }

    /**
     * Exception Handler getter
     * @param handlerMethod HandlerMethod
     * @param exception Exception
     * @return ServletInvocableHandlerMethod containing new exceptionhandler and method.
     */
    protected ServletInvocableHandlerMethod getExceptionHandlerMethod(HandlerMethod handlerMethod,
                                                                      Exception exception) {
        Object exceptionHandler = null;

        try {
            exceptionHandler = exceptionHandlerClass.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(String.format(ERROR_MSG, exceptionHandlerClass.getCanonicalName()), e);
        }

        Method method = new ExceptionHandlerMethodResolver(exceptionHandlerClass).resolveMethod(exception);
        return new ServletInvocableHandlerMethod(exceptionHandler, method);
    }
}