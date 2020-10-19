package com.example.generateexcel

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import spock.lang.Specification

class ExcelServiceSpec extends Specification {

    def 'Should return excel with Hello World in first cell'() {
        given:
        def excelService = new ExcelService()

        when:
        def result = excelService.generateHelloWorldExcel()

        then:
        Sheet sheet = result.getSheetAt(0)
        Row row = sheet.getRow(0)
        Cell cell = row.getCell(0)
        "Hello World" == cell.getStringCellValue()
    }
}
