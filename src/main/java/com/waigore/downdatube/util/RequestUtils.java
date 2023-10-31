package com.waigore.downdatube.util;

import org.javatuples.Pair;

public class RequestUtils {
    /**
     * Converts range construct from frontend (in inclusive to/from format) to format required by Spring JPA (pageNumber/pageSize)
     * @param frontendRange
     * @return
     */
    public static Pair<Integer, Integer> frontendToBackendRange(Pair<Integer, Integer> frontendRange) {
        int fromRecord = frontendRange.getValue0();
        int toRecord = frontendRange.getValue1();

        int pageSize = toRecord - fromRecord + 1;
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Range (" + fromRecord + "," + toRecord + ") is invalid!" );
        }
        if (fromRecord % pageSize != 0) {
            throw new IllegalStateException("Range (" + fromRecord + "," + toRecord + ") is not a multiple of page size!");
        }

        int pageNum = toRecord / pageSize;
        return new Pair<Integer, Integer>(pageNum, pageSize);
    }
}
