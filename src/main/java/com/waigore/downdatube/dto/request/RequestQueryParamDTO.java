package com.waigore.downdatube.dto.request;

import com.waigore.downdatube.util.QueryPagingUtils;
import lombok.*;
import org.javatuples.Pair;

import java.util.Map;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestQueryParamDTO {
    Map<String, String> filter;
    Pair<Integer, Integer> range;
    Pair<String, String> sort;

    public Pair<Integer, Integer> asJpaRange() {
        return QueryPagingUtils.frontendToBackendRange(range);
    }
}
