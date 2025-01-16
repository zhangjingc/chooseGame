package zjc;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;
public class createSql {

    public static void main(String[] args) {
        String excelFilePath = System.getProperty("user.home") + "/Desktop/input.xlsx";
        String outputFilePath = System.getProperty("user.home") + "/Desktop/output.sql";

        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis);
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0) continue; // 跳过表头

                String contractCode = getCellValue(row.getCell(1));
                String customerId = getCellValue(row.getCell(7));
                String code = getCellValue(row.getCell(6));

                String sql = String.format("UPDATE distributor SET contract_code = '%s', customer_id = '%s' WHERE code = '%s';\n",
                        contractCode, customerId, code);

                writer.write(sql);
            }

            System.out.println("SQL statements have been generated and saved to " + outputFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            default:
                return "";
        }
    }
}
