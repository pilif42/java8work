package com.philippe.app.generics;

import com.jayway.jsonpath.DocumentContext;
import com.philippe.app.domain.IndexWrapper;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class using generic methods.
 */
public class Util {

    private static final String INDEX_WRAPPER_CLASS_NAME = "IndexWrapper";

    /**
     * Usage is:
     *
     * final List<IndexWrapper> indexes = populateIndexes(primaryRowId, documentContext, IndexWrapper.class);
     *
     */
    public static <T> List<T> populateIndexes(final ByteBuffer primaryRowId,
                                              final DocumentContext documentContext,
                                              final Class<T> type) {
        List<T> resultList = new ArrayList<>();

        final String typeClassName = type.getSimpleName();
        if (typeClassName.equalsIgnoreCase(INDEX_WRAPPER_CLASS_NAME)) {
            resultList.add(type.cast(new IndexWrapper(primaryRowId)));
        } else {
            // TODO
        }

        return resultList;
    }
}
