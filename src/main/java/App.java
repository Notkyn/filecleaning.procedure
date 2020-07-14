import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.ArgValidator;
import tools.FilterFile;
import tools.OperationFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args){
        logger.info("Starting the procedure for checking the file retention period!");

        if(ArgValidator.isCorrectArguments(args)){

            Path directoryPath = Paths.get(args[0]);
            logger.info("Directory: " + directoryPath);

            int monthPeriod = Integer.parseInt(args[1]);
            logger.info("Period (month): " + monthPeriod);

            ArrayList<Path> files = FilterFile.getFiles(directoryPath, monthPeriod);
            logger.info("Found files: " + files.size());

            logger.info("File deletion started...");
            logger.info("Files deleted: " + OperationFile.deleteFiles(files));
        }

        logger.info("Stop procedure!");
    }
}