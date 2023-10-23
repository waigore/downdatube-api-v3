package com.waigore.downdatube.repository;

import com.waigore.downdatube.entity.DownloadEntry;
import org.javatuples.Pair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DownloadEntryRepository extends PagingAndSortingRepository<DownloadEntry, Long> {
    public Page<DownloadEntry> findByUploader(String uploader, Pageable pageable);

}
