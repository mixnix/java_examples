package com.example.generateexcel;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/api/excels")
public class ExcelController {

    //I should inject through constructor, not through autowiring. But I didn't want to add Lombok dependency
    @Autowired
    ExcelService excelService;

    //simplest example
    @GetMapping("/hello-world")
    public ResponseEntity<StreamingResponseBody> helloWorldExcel() {
        Workbook workbook = excelService.generateHelloWorldExcel();
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                //attachment
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=myfilename.xlsx")
                .body(workbook::write);
    }

    //creates sorted excel
    @GetMapping("/sorted-excel")
    public ResponseEntity<StreamingResponseBody> sortedExcel() {
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                //attachment
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=myfilename.xlsx")
                .body(excelService.generateFilteredExcel()::write);
    }
}
