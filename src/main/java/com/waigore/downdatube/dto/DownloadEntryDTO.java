package com.waigore.downdatube.dto;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DownloadEntryDTO {
    private String videoId;
    private Long id;
    private String name;
    private String url;
    private Long playlistId;
    private Integer playlistIndex;
    private String queueDate;
    private String uploadDate;
    private String downloadDate;
    private Boolean downloadAudio;
    private String uploader;
    private String status;
    private Integer statusPct;
}
