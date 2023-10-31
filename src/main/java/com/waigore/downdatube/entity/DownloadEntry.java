package com.waigore.downdatube.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DownloadEntry {
    public enum Status {
        INITIAL, DOWNLOADING, PAUSED, ERROR, FINISHED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "playlistId", nullable = true)
    private Playlist playlist;

    private Integer playlistIndex;

    private String videoId;

    private String name;

    private String uploader;

    private String source;

    private String url;

    private String filename;

    private String format;

    private Boolean downloadAudio;

    private Timestamp uploadDate;

    private Timestamp queueDate;

    private Timestamp downloadDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String statusReason;
}
