package com.andrenunes.document_reader.services;

import com.andrenunes.document_reader.exceptions.InvalidExtensionException;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class ReadDocumentService {
    public static List<List<String>> readExcel(MultipartFile file) throws IOException {
        if(Objects.isNull(file.getContentType())){ throw new FileNotFoundException("Please send file!");}
        List<List<String>> dataList = new ArrayList<>();
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        if(!fileName.toLowerCase().endsWith(".xlsx")){
            throw new InvalidExtensionException(FilenameUtils.getExtension(fileName));
        }
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            sheet.forEach(cells -> {
                List<String> rowData = new ArrayList<>();
                cells.forEach(cell -> {
                    String cellValue = switch (cell.getCellType()) {
                        case STRING -> cell.getStringCellValue();
                        case NUMERIC -> String.valueOf(cell.getNumericCellValue());
                        case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
                        default -> "X";
                    };
                    rowData.add(cellValue);
                });
                dataList.add(rowData);
            });
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException(e.getMessage());
        }
        return dataList;
    }

}
