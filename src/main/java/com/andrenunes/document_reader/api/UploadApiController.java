package com.andrenunes.document_reader.api;

import com.andrenunes.document_reader.services.ReadDocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
public class UploadApiController {

    private final ReadDocumentService readDocumentService;

    public UploadApiController(ReadDocumentService readDocumentService) {
        this.readDocumentService = readDocumentService;
    }


    @PostMapping("/upload")
    public ResponseEntity<List<List<String>>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        List<List<String>> lists = readDocumentService.readExcel(file);
        return ResponseEntity.ok(lists);
    }

}
