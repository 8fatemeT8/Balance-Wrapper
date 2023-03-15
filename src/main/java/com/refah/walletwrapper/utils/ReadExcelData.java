package com.refah.walletwrapper.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class ReadExcelData {

    public static ArrayList<ArrayList<String>> importExcelData(MultipartFile input) {
        ArrayList<ArrayList<String>> excelData = new ArrayList<>();
        try {
            InputStream file = input.getInputStream();
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                ArrayList<String> rowDetail = new ArrayList<>();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case NUMERIC:
                            rowDetail.add(new Double(cell.getNumericCellValue()).longValue() + "");
                            break;
                        case STRING:
                            rowDetail.add(cell.getStringCellValue());
                            break;
                    }
                }
                excelData.add(rowDetail);
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return excelData;
    }
}
