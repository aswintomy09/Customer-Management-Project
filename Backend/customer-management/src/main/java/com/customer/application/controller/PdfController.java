package com.customer.application.controller;

import com.customer.application.service.PdfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.customer.application.constants.Constants.CUSTOMER_URL;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(CUSTOMER_URL)
@Slf4j
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @GetMapping("/customer/{customerId}/download")
    public ResponseEntity<byte[]> downloadInvoice(@PathVariable Long customerId) {

        byte[] pdf = pdfService.downloadPdf(customerId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; Bill-" + customerId + ".pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdf);
    }

}
