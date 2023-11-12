package com.waigore.downdatube.controller;

import com.waigore.downdatube.dto.DownloadEntryDTO;
import com.waigore.downdatube.service.DownloadEntryService;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/downloads")
public class DownloadController {
    @Autowired
    private DownloadEntryService downloadEntryService;

    @GetMapping
    public @ResponseBody List<DownloadEntryDTO> getDownloads(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) Pair<Integer, Integer> range,
            @RequestParam(required = false) Pair<String, String> sort) {
        return downloadEntryService.findEntries(range, sort);
    }
}
