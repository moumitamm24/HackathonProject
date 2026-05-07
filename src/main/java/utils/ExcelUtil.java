package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    public static void writeToExcel(String filePath,
                                    String sheetName,
                                    List<String[]> data) {

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet(sheetName);

            int rowNum = 0;
            for (String[] rowData : data) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 0; i < rowData.length; i++) {
                    row.createCell(i).setCellValue(rowData[i]);
                }
            }

            File file = new File(filePath);
            file.getParentFile().mkdirs();

            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}