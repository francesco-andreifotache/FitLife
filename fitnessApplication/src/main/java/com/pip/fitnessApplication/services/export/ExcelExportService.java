package com.pip.fitnessApplication.services.export;

import com.pip.fitnessApplication.entity.Activity;
import com.pip.fitnessApplication.entity.Workout;
import com.pip.fitnessApplication.repository.ActivityRepository;
import com.pip.fitnessApplication.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelExportService {

    private final ActivityRepository activityRepository;
    private final WorkoutRepository workoutRepository;

    public byte[] exportUserDataToExcel(Long userId) throws IOException {
        
        List<Activity> activities = activityRepository.findAllByUserId(userId);
        List<Workout> workouts = workoutRepository.findAllByUserId(userId);

        
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            
            
            Sheet activitySheet = workbook.createSheet("Activitati");
            createActivitySheet(activitySheet, activities, workbook);

            
            Sheet workoutSheet = workbook.createSheet("Antrenamente");
            createWorkoutSheet(workoutSheet, workouts, workbook);

            
            workbook.write(out);
            return out.toByteArray();
        }
    }

    private void createActivitySheet(Sheet sheet, List<Activity> activities, Workbook workbook) {
        
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        
        Row headerRow = sheet.createRow(0);
        String[] columns = {"ID", "Data", "Pasi", "Distanta (km)", "Calorii Arse"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerStyle);
        }

        
        int rowIdx = 1;
        for (Activity activity : activities) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(activity.getId());
            row.createCell(1).setCellValue(activity.getDate() != null ? activity.getDate().toString() : "");
            row.createCell(2).setCellValue(activity.getSteps());
            row.createCell(3).setCellValue(activity.getDistance());
            
            row.createCell(4).setCellValue(activity.getCaloriesBurned());
        }
        
        
        for (int i = 0; i < columns.length; i++) { sheet.autoSizeColumn(i); }
    }

    private void createWorkoutSheet(Sheet sheet, List<Workout> workouts, Workbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Row headerRow = sheet.createRow(0);
        String[] columns = {"ID", "Data", "Tip Antrenament", "Durata (min)", "Calorii Arse"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerStyle);
        }

        int rowIdx = 1;
        for (Workout workout : workouts) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(workout.getId());
            row.createCell(1).setCellValue(workout.getDate() != null ? workout.getDate().toString() : "");
            row.createCell(2).setCellValue(workout.getType() != null ? workout.getType() : "");
            // duration and caloriesBurned are primitives (int), so assign directly
            row.createCell(3).setCellValue(workout.getDuration());
            row.createCell(4).setCellValue(workout.getCaloriesBurned());
        }

        for (int i = 0; i < columns.length; i++) { sheet.autoSizeColumn(i); }
    }
}