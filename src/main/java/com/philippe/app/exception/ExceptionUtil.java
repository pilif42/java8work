package com.philippe.app.exception;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class ExceptionUtil {

    private static final String EXCEPTION_CREATED_X_TIME = "%s (called %d times)";

    private ExceptionUtil() {
        // Add a private constructor to hide the implicit public one.
    }

    /**
     * Consolidates a set of Exceptions, ie:
     *      - 1) removes duplicates.
     *      - 2) adds a count on exceptions kept in the resulting set.
     *
     * @param exceptionSet the input set
     * @return the consolidated set
     */
    public static Set<ComparableException> consolidate(final Set<Exception> exceptionSet) {
        final Set<ComparableException> finalSet = new HashSet<>();
        final BiConsumer<ComparableException, Long> convert = (comparableException, count) -> {
            if (count > 1) {
                finalSet.add(map(comparableException, count));
            } else {
                finalSet.add(comparableException);
            }
        };

        exceptionSet.stream().map(ExceptionUtil::map)
                .collect(Collectors.groupingBy(comparableException -> comparableException, Collectors.counting()))
                .forEach(convert);
        return finalSet;
    }

    private static ComparableException map(final Exception exception) {
        final ComparableException comparableException = new ComparableException(exception.getMessage(),
                exception.getCause());
        comparableException.setStackTrace(exception.getStackTrace());
        return comparableException;
    }

    private static ComparableException map(final ComparableException comparableException, final Long count) {
        final ComparableException result = new ComparableException(
                format(EXCEPTION_CREATED_X_TIME, comparableException.getMessage(), count),
                comparableException.getCause());
        result.setStackTrace(comparableException.getStackTrace());
        return result;
    }
}
