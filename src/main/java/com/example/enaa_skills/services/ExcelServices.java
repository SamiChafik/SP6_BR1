package com.example.enaa_skills.services;

import com.example.enaa_skills.entities.Competence;
import com.example.enaa_skills.entities.SubCompetence;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class ExcelServices {


    public void exportCompetencesToExcel(List<Competence> competences, OutputStream outputStream) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Rapport de Compétences");

            // Style pour les en-têtes
            CellStyle headerStyle = workbook.createCellStyle();
            XSSFFont headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeight(14);
            headerStyle.setFont(headerFont);

            // Style pour cellule validée (vert)
            CellStyle greenStyle = workbook.createCellStyle();
            greenStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            greenStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Style pour cellule non validée (rouge)
            CellStyle redStyle = workbook.createCellStyle();
            redStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            redStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Ligne d'en-tête
            Row headerRow = sheet.createRow(0);
            String[] headers = {
                    "ID Compétence", "Titre Compétence", "Description", "Validation",
                    "ID Sous-Compétence", "Titre Sous-Compétence", "Description", "Validation"
            };

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }


            int rowNum = 1;
            for (Competence competence : competences) {
                for (SubCompetence subCompetence : competence.getSubCompetenceList()) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(competence.getId());
                    row.createCell(1).setCellValue(competence.getName());
                    row.createCell(2).setCellValue(competence.getDescription());
//                    row.createCell(3).setCellValue(competence.isValidated());
                    row.createCell(4).setCellValue(subCompetence.getId());
                    row.createCell(5).setCellValue(subCompetence.getName());
                    row.createCell(6).setCellValue(subCompetence.getDescription());
//                    row.createCell(7).setCellValue(subCompetence.isValidated());

                    Cell validationCell = row.createCell(3);
                    boolean estValide = subCompetence.isValidated();
                    validationCell.setCellValue(estValide ? "Oui" : "Non");
                    validationCell.setCellStyle(estValide ? greenStyle : redStyle);
//                    row.createCell(3).setCellValue(competences.getFirst().getDescription());

                    Cell validationCell2 = row.createCell(7);
                    boolean estValide2 = subCompetence.isValidated();
                    validationCell2.setCellValue(estValide2 ? "Oui" : "Non");
                    validationCell2.setCellStyle(estValide2 ? greenStyle : redStyle);
//                    row.createCell(7).setCellValue(competences.getFirst().getDescription());
                }
            }

            // Écriture du fichier Excel
            workbook.write(outputStream);
        }
    }
}