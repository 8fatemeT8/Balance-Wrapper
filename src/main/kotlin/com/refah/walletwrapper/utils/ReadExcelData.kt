package com.refah.walletwrapper.utils

import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.web.multipart.MultipartFile


class ReadExcelData {

    companion object {
        fun importExcelData( file2 : MultipartFile): ArrayList<ArrayList<String>> {
            val excelData = ArrayList<ArrayList<String>>()
            try {
                val file = file2.inputStream
                val workbook = XSSFWorkbook(file)
                val sheet: XSSFSheet = workbook.getSheetAt(0)
                val rowIterator: Iterator<Row> = sheet.iterator()

                while (rowIterator.hasNext()) {
                    val row: Row = rowIterator.next()
                    val cellIterator = row.cellIterator()
                    val rowDetail = ArrayList<String>()
                    while (cellIterator.hasNext()) {
                        val cell = cellIterator.next()
                        when (cell.cellType) {
                            CellType.NUMERIC -> rowDetail.add(cell.numericCellValue.toInt().toString() + "")
                            CellType.STRING -> rowDetail.add(cell.stringCellValue)
                        }
                    }
                    excelData.add(rowDetail)
                }
                file.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return excelData
        }
    }
}