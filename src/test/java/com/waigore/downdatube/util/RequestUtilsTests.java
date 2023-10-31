package com.waigore.downdatube.util;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RequestUtilsTests {
    @Test
    void testFrontendToBackendRangeConversion() {
        Pair<Integer, Integer> convertedPair = RequestUtils.frontendToBackendRange(new Pair(0, 9));
        assertThat(convertedPair.getValue0()).isEqualTo(0);
        assertThat(convertedPair.getValue1()).isEqualTo(10);

        convertedPair = RequestUtils.frontendToBackendRange(new Pair(20, 39));
        assertThat(convertedPair.getValue0()).isEqualTo(1);
        assertThat(convertedPair.getValue1()).isEqualTo(20);

        convertedPair = RequestUtils.frontendToBackendRange(new Pair(1, 1));
        assertThat(convertedPair.getValue0()).isEqualTo(1);
        assertThat(convertedPair.getValue1()).isEqualTo(1);

        assertThatThrownBy(() -> {
            Pair newPair = RequestUtils.frontendToBackendRange(new Pair(1, 0));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalid");

        assertThatThrownBy(() -> {
            Pair newPair = RequestUtils.frontendToBackendRange(new Pair(11, 20));
        }).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("not a multiple");
    }
}
