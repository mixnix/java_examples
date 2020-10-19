package com.example.generateexcel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

//Should create interface and then its implementation. But it's just simple example
@Service
public class ExcelService {

    public Workbook generateHelloWorldExcel(){
        Workbook workBook = new XSSFWorkbook();
        Sheet sheet = workBook.createSheet("My Sheet");

        sheet.setColumnWidth(0, 2560);
        sheet.setColumnWidth(1, 2560);
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Hello World");
        return workBook;
    }

    public Workbook generateFilteredExcel(){
        Workbook workBook = new XSSFWorkbook();
        Sheet sheet = workBook.createSheet("My Sheet");
        sheet.setAutoFilter(new CellRangeAddress(0, 4, 0, 0));
        sheet.setColumnWidth(0, 2560);
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Zello World");
        row = sheet.createRow(1);
        row.createCell(0).setCellValue("Rello World");
        row = sheet.createRow(2);
        row.createCell(0).setCellValue("Kello World");
        return workBook;
    }
}
