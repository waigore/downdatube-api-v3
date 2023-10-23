package com.waigore.downdatube.mapper;

import com.waigore.downdatube.dto.DownloadEntryDTO;
import com.waigore.downdatube.entity.DownloadEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface DownloadEntryMapper {
    @Mappings ({
            @Mapping(target = "playlistId", source = "entry.playlist.id"),
            @Mapping(target = "queueDate", dateFormat = "yyyy-MM-dd'T'HH:mm:ssZ"),
            @Mapping(target = "statusPct", ignore = true)
    })
    DownloadEntryDTO entityToDto(DownloadEntry entry);
}
