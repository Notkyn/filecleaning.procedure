package tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class OperationFile {
    private static final Logger logger = LoggerFactory.getLogger(OperationFile.class);

    public static int deleteFiles(ArrayList<Path> files){
        int count = 0;

        for(Path file : files){
            try {
                Files.delete(file);
                count++;
            } catch (IOException ex) {
                logger.warn("Failed to delete file.: " + file);
            }
        }

        return count;
    }
}
