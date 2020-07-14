package tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

class AttributeHelper {
    private static final Logger logger = LoggerFactory.getLogger(AttributeHelper.class);

    static LocalDate getCreationDate(Path file){
        LocalDate creationDate;

        try{
            BasicFileAttributeView basicfile = Files.getFileAttributeView(file,
                    BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
            BasicFileAttributes attr = basicfile.readAttributes();
            long date = attr.creationTime().toMillis();
            Instant instant = Instant.ofEpochMilli(date);
            creationDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        } catch (IOException e){
            creationDate = LocalDate.now(ZoneId.systemDefault());

            logger.warn("Failed to read file attributes: " + file);
        }

        return creationDate;
    }
}
