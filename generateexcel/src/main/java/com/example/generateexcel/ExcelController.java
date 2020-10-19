package com.example.generateexcel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api/excels")
public class ExcelController {

    //I should inject through constructor, not through autowiring. But I didn't want to add Lombok dependency
    @Autowired
    ExcelService excelService;

    //simplest example
    @GetMapping("/hello-world")
    public ResponseEntity<StreamingResponseBody> helloWorldExcel() {
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                //attachment
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=myfilename.xlsx")
                .body(excelService.generateHelloWorldExcel()::write);
    }

//    @GetMapping("/hello-world-byte-array")
//    public ResponseEntity<ByteArrayOutputStream> helloWorldExcelButWithByteArray() {
//        return ResponseEntity
//                .ok()
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=myfilename.xlsx")
//                .body(excelService.generateHelloWorldExcel()::write);
//    }
}
