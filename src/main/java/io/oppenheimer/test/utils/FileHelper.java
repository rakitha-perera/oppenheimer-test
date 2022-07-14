package io.oppenheimer.test.utils;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileHelper {

    public static boolean fileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    public static List<String> readFileContent(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllLines(path);
    }

    public static boolean deleteFile(String filePath) {
        boolean isDeleted = false;
        File file = new File(filePath);
        if(fileExist(filePath)){
            isDeleted = file.delete();
        }
        return isDeleted;
    }

    public static <CsvType> void writeToCsv(String path, List<CsvType> data) throws IOException {
        try (Writer writer = new FileWriter(path)) {

            StatefulBeanToCsv<CsvType> sbc = new StatefulBeanToCsvBuilder<CsvType>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();
            sbc.write(data);
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
    }
}
