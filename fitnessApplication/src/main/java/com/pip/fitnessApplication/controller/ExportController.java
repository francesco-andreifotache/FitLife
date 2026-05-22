package com.pip.fitnessApplication.controller;

import com.pip.fitnessApplication.services.export.ExcelExportService;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/export")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExportController {

    private final ExcelExportService excelExportService;

   @GetMapping("/excel/{userId}")
    public ResponseEntity<byte[]> exportExcel(@PathVariable Long userId) throws IOException {
    byte[] excelContent = excelExportService.exportUserDataToExcel(userId);

    return ResponseEntity.ok()
            // Numele fișierului
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=FitLife_Raport.xlsx")
            // ---- MODIFICARE: Forțăm browserul să NU salveze în cache ----
            .header(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate")
            .header(HttpHeaders.PRAGMA, "no-cache")
            .header(HttpHeaders.EXPIRES, "0")
            // -------------------------------------------------------------
            .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            .body(excelContent);
}
}