package com.waigore.downdatube.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Playlist {
    @Id
    @GeneratedValue
    private Long id;

    private String playlistId;

    private String uploader;

    private String name;

    private String title;

    private String url;

    private String status;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
    private List<DownloadEntry> downloadEntries;
}
