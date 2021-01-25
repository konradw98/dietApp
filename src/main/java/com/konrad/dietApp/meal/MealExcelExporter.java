package com.konrad.dietApp.meal;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MealExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Meal> listMeals;

    public MealExcelExporter(List<Meal> listMeals) {
        this.listMeals = listMeals;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Meal ID", style);
        createCell(row, 1, "Carbo", style);
        createCell(row, 2, "Fat", style);
        createCell(row, 3, "Kcal", style);
        createCell(row, 4, "Portions", style);
        createCell(row, 5, "Protein", style);
        createCell(row, 6, "E-mail", style);
        createCell(row, 7, "date", style);



    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else if(value instanceof Float){
            cell.setCellValue( value.toString());} else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Meal meal : listMeals) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, meal.getId(), style);
            createCell(row, columnCount++, meal.getCarbo(), style);
            createCell(row, columnCount++, meal.getFat(), style);
            createCell(row, columnCount++, meal.getKcal(), style);
            createCell(row, columnCount++, meal.getName(), style);
            createCell(row, columnCount++, meal.getPortions(), style);
            createCell(row, columnCount++, meal.getEmail(), style);
            LocalDate localDate=meal.getDate();
            if(localDate==null){
                localDate=LocalDate.of(01,01,01);
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            String formattedString = localDate.format(formatter);
            createCell(row, columnCount++, formattedString, style);


        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}