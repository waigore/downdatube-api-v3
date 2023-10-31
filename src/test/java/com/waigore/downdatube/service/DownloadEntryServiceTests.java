package com.waigore.downdatube.service;

import com.waigore.downdatube.dto.DownloadEntryDTO;
import com.waigore.downdatube.entity.DownloadEntry;
import com.waigore.downdatube.repository.DownloadEntryRepository;
import org.javatuples.Pair;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
        given(downloadEntryRepository.findAll(any(Sort.class))).willAnswer(d -> {
            return allDownloadEntries;
        });

        List<DownloadEntryDTO> dtos = downloadEntryService.findAllEntries();
        assertThat(dtos).isNotEmpty();
        assertThat(dtos).hasSize(2);
    }

    @Test
    void testFindEntriesWithRangeWorks() {
        given(downloadEntryRepository.findAll(any(Pageable.class))).willAnswer(i -> {
            Pageable p = i.getArgument(0);
            int fromRecord = p.getPageNumber() * p.getPageSize();
            int toRecord = fromRecord + p.getPageSize();
            return new PageImpl(allDownloadEntries.subList(fromRecord, toRecord));
        });

        List<DownloadEntryDTO> dtos = downloadEntryService.findEntries(new Pair(0, 1), null);
        assertThat(dtos).isNotEmpty();
        assertThat(dtos).hasSize(1);

        DownloadEntryDTO dto = dtos.get(0);
        assertThat(dto.getVideoId()).isEqualTo("6jWNcraW4Go");


        dtos = downloadEntryService.findEntries(new Pair(1, 1), null);
        assertThat(dtos).hasSize(1);

        dto = dtos.get(0);
        assertThat(dto.getVideoId()).isEqualTo("e4Uo3nKVx4A");
    }
}
