package com.waigore.downdatube.service;

import com.waigore.downdatube.dto.DownloadEntryDTO;
import com.waigore.downdatube.entity.DownloadEntry;
import com.waigore.downdatube.repository.DownloadEntryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class DownloadEntryServiceTests {
    private static final Logger LOG = LoggerFactory.getLogger(DownloadEntryServiceTests.class);

    @MockBean
    private DownloadEntryRepository downloadEntryRepository;

    @InjectMocks
    @Autowired
    private DownloadEntryService downloadEntryService;

    private List<DownloadEntry> allDownloadEntries;

    @BeforeEach
    void setUp() {
        allDownloadEntries = List.of(
            DownloadEntry.builder()
                    .videoId("6jWNcraW4Go")
                    .name("Video #1")
                    .uploader("Premier League")
                    .status(DownloadEntry.Status.FINISHED)
                    .build(),
                DownloadEntry.builder()
                        .videoId("e4Uo3nKVx4A")
                        .name("Video #2")
                        .uploader("memehongkong")
                        .status(DownloadEntry.Status.DOWNLOADING)
                        .build()
        );
    }

    @Test
    void testFindAllEntriesWorks() {
        given(downloadEntryRepository.findAll((Sort) any())).willAnswer(d -> {
            return allDownloadEntries;
        });

        List<DownloadEntryDTO> dtos =downloadEntryService.findAllEntries();
        assertThat(dtos).isNotEmpty();
        assertThat(dtos).hasSize(2);
    }
}
