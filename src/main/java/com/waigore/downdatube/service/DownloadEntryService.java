package com.waigore.downdatube.service;

import com.waigore.downdatube.dto.DownloadEntryDTO;
import com.waigore.downdatube.entity.DownloadEntry;
import com.waigore.downdatube.mapper.DownloadEntryMapper;
import com.waigore.downdatube.repository.DownloadEntryRepository;
import com.waigore.downdatube.util.QueryPagingUtils;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class DownloadEntryService {
    @Autowired
    private DownloadEntryRepository downloadEntryRepository;

    @Autowired
    private DownloadEntryMapper downloadEntryMapper;

    public List<DownloadEntryDTO> findAllEntries() {
        return findEntries(null, null);
    }

    public List<DownloadEntryDTO> findEntries(
            Pair<Integer, Integer> range,
            Pair<String, String> sort) {
        Page<DownloadEntry> page = downloadEntryRepository.findAll(
                QueryPagingUtils.buildPageableFrom(range, sort)
        );
        return page.get().map(d -> downloadEntryMapper.entityToDto(d)).toList();
    }

    public List<DownloadEntryDTO> findEntriesByUploader(
            String uploader,
            Pair<Integer, Integer> range,
            Pair<String, String> sort) {
        Page<DownloadEntry> page = downloadEntryRepository.findByUploader(
                uploader, QueryPagingUtils.buildPageableFrom(range, sort)
        );
        return page.get().map(d -> downloadEntryMapper.entityToDto(d)).toList();
    }
}
