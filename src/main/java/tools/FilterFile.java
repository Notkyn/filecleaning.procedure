package tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FilterFile{
    private static final Logger logger = LoggerFactory.getLogger(FilterFile.class);

    public static ArrayList<Path> getFiles(Path path, int period) {
        ArrayList<Path> files;

        try{
            files = Files.list(path)
                    .filter(p -> !Files.isDirectory(p))
                    .filter(p -> getCountMonth(AttributeHelper.getCreationDate(p)) > period)
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            files = new ArrayList<>();

            logger.warn("Failed to find files in directory: " + path);
        }

        return files;
    }

    private static int getCountMonth(LocalDate fileDate){
        LocalDate currentDate = LocalDate.now(ZoneId.systemDefault());

        if(fileDate == null){
            fileDate = currentDate;
        }

        int count = 0;
        int countYear = currentDate.getYear() - fileDate.getYear();

        if(countYear == 0) {
            count = currentDate.getMonth().getValue() - fileDate.getMonth().getValue();
        }

        if(countYear == 1) {
            count = 12 - fileDate.getMonth().getValue() + currentDate.getMonth().getValue();
        }

        if(countYear > 1) {
            count = (countYear - 1) * 12 + 12 - fileDate.getMonth().getValue() + currentDate.getMonth().getValue();
        }

        return count;
    }

}
