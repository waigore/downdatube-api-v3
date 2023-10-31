package com.waigore.downdatube.util;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class JsonConversionTests {
    @Test
    void testJsonToIntPairConversion() {
        StringToPairConverter converter = new StringToPairConverter<Integer>();
        String jsonString = "[1,2]";
        Pair<Integer, Integer> pair = converter.convert(jsonString);
        assertThat(pair.getValue0()).isEqualTo(1);
        assertThat(pair.getValue1()).isEqualTo(2);
    }

    @Test
    void testJsonToStringPairConversion() {
        StringToPairConverter converter = new StringToPairConverter<String>();
        String jsonString = "[\"id\", \"DESC\"]";
        Pair<String, String> pair = converter.convert(jsonString);
        assertThat(pair.getValue0()).isEqualTo("id");
        assertThat(pair.getValue1()).isEqualTo("DESC");
    }

}
