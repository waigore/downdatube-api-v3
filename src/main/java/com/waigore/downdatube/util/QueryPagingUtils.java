package com.waigore.downdatube.util;

import org.javatuples.Pair;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class QueryPagingUtils {
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

    public static Pageable buildPageableFrom(Pair<Integer, Integer> range,
                                      Pair<String, String> sort) {
        int pageNumber = range == null ? 0 : range.getValue0();
        int pageSize = range == null ? Integer.MAX_VALUE : range.getValue1();
        Sort jpaSort = sort == null ? Sort.unsorted() : Sort.by(Sort.Direction.fromString(sort.getValue0()), sort.getValue1());

        return PageRequest.of(pageNumber, pageSize, jpaSort);
    }
}
