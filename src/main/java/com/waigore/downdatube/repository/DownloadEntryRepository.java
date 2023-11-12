package com.waigore.downdatube.repository;

import com.waigore.downdatube.entity.DownloadEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;

@Repository
public interface DownloadEntryRepository extends PagingAndSortingRepository<DownloadEntry, Long> {
    public Page<DownloadEntry> findByUploader(
            String uploader,
            @PageableDefault(page= 0, value = Integer.MAX_VALUE) Pageable pageable
    );

    public Page<DownloadEntry> findAll(
            @PageableDefault(page= 0, value = Integer.MAX_VALUE) Pageable pageable
    );

}
