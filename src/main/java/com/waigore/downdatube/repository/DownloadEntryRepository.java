package com.waigore.downdatube.repository;

import com.waigore.downdatube.entity.DownloadEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DownloadEntryRepository extends JpaRepository<DownloadEntry, Long> {
}
