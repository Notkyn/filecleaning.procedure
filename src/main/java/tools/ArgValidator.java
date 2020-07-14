package tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ArgValidator {
    private static final Logger logger = LoggerFactory.getLogger(ArgValidator.class);

    public static boolean isCorrectArguments(String[] args){
        try{
            return ArgValidator.isCorrectDirectory(args[0])
                    && ArgValidator.isCorrectPeriod(args[1]);
        } catch (ArrayIndexOutOfBoundsException ex) {
            logger.warn("Arguments are not correct or absent!");
            return false;
        }
    }

    private static boolean isCorrectDirectory(String path){
        return path != null && path.length() > 0 && Files.isDirectory(Paths.get(path));
    }

    private static boolean isCorrectPeriod(String argPeriod){
        try {
            Integer.parseInt(argPeriod);
            return true;
        } catch (NumberFormatException ex) {
            logger.warn("Argument of perion are not correct or absent!");
            return false;
        }
    }
}
