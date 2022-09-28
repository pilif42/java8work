package com.philippe.app.aop;

//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a sample class to illustrate what can be achieved with Spring AOP. The idea here is that before a Sample is
 * stored using SampleRepository, the call will be intercepted and for instance we will modify one field within the Sample
 * in the method updateSampleBeforeItIsSaved.
 *
 * Dependencies required in your POM:
 *         <dependency>
 *             <groupId>org.springframework</groupId>
 *             <artifactId>spring-aop</artifactId>
 *         </dependency>
 *         <dependency>
 *             <groupId>org.aspectj</groupId>
 *             <artifactId>aspectjrt</artifactId>
 *             <version>1.9.4</version>
 *         </dependency>
 *         <dependency>
 *             <groupId>org.aspectj</groupId>
 *             <artifactId>aspectjweaver</artifactId>
 *             <version>1.9.4</version>
 *         </dependency>
 *
 * Notice the @Aspect and @Component that have been commented out. Both are required.
 *
 * Also required is @EnableAspectJAutoProxy on your Spring Boot application class. (import org.springframework.context.annotation.EnableAspectJAutoProxy;)
 */
//@Aspect
//@Component
public class ExampleUsingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleUsingAspect.class);

//    @Pointcut("execution(* *.save(..))")
//    private void saveMethod() {
//        // Called on any method named save, with any arguments, in any package.
//    }
//
//    @Pointcut("execution(* *.saveAndFlush(..))")
//    private void saveAndFlushMethod() {
//        // Called on any method named saveAndFlush, with any arguments, in any package.
//    }
//
//    @Pointcut("execution(* *.saveAll(..))")
//    private void saveAllMethod() {
//        // Called on any method named saveAll, with any arguments, in any package.
//    }
//
//    @Pointcut("this(com.philippe.repository.SampleRepository)")
//    private void sampleRepository() {
//        // Only for SampleRepository.
//    }
//
//    @Pointcut("sampleRepository() && (saveMethod() || saveAndFlushMethod() || saveAllMethod())")
//    private void sampleRepositorySaveMethods() {
//        // On save of sampleRepository.
//    }
//
//    @Around("sampleRepositorySaveMethods() && args(com.philippe.jpa.Sample)")
//    public Object updateSampleBeforeItIsSaved(final ProceedingJoinPoint pjp) throws Throwable {
//        LOGGER.debug("Entering updateSampleBeforeItIsSaved ...");
//        final Object argObj = (pjp.getArgs())[0];
//        if (argObj == null) {
//        }
//        return null;
//    }
}
