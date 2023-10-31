package com.waigore.downdatube.service;

import com.waigore.downdatube.dto.DownloadEntryDTO;
import com.waigore.downdatube.entity.DownloadEntry;
import com.waigore.downdatube.mapper.DownloadEntryMapper;
import com.waigore.downdatube.repository.DownloadEntryRepository;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        return findAllEntries(null);
    }

    public List<DownloadEntryDTO> findAllEntries(Pair<String, String> sort) {
        Sort jpaSort = sort == null ? Sort.unsorted() : Sort.by(Sort.Direction.fromString(sort.getValue0()), sort.getValue1());
        Iterable<DownloadEntry> it = downloadEntryRepository.findAll(jpaSort);
        return StreamSupport.stream(it.spliterator(), false)
                .map(d -> downloadEntryMapper.entityToDto(d))
                .toList();
    }

    public List<DownloadEntryDTO> findEntries(
            Pair<Integer, Integer> range,
            Pair<String, String> sort) {
        Sort jpaSort = sort == null ? Sort.unsorted() : Sort.by(Sort.Direction.fromString(sort.getValue0()), sort.getValue1());
        Page<DownloadEntry> page = downloadEntryRepository.findAll(
                PageRequest.of(range.getValue0(), range.getValue1(), jpaSort)
        );
        return page.get().map(d -> downloadEntryMapper.entityToDto(d)).toList();
    }

    public List<DownloadEntryDTO> findEntriesByUploader(
            String uploader,
            Pair<Integer, Integer> range,
            Pair<String, String> sort) {
        Sort jpaSort = sort == null ? Sort.unsorted() : Sort.by(Sort.Direction.fromString(sort.getValue0()), sort.getValue1());
        Page<DownloadEntry> page = downloadEntryRepository.findByUploader(
                uploader, PageRequest.of(range.getValue0(), range.getValue1(), jpaSort)
        );
        return page.get().map(d -> downloadEntryMapper.entityToDto(d)).toList();
    }
}
