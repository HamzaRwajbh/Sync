package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileAttributeUtil {

    private static final SimpleDateFormat PATTERN = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static final FileAttributeUtil INSTANCE = new FileAttributeUtil();

    private FileAttributeUtil() {
    }

    public static FileAttributeUtil getInstance() {
        return INSTANCE;
    }

    public Date lastModifiedTime(File file)  {
        try {
            BasicFileAttributes attribute = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            FileTime date = attribute.lastModifiedTime();
            return PATTERN.parse(PATTERN.format(date.toMillis()));
        } catch (IOException e) {
            e.getMessage();
            return null;
        }catch (ParseException e){
            e.getMessage();
            return  null ;
        }
    }

    public Date lastAccessTime(File file){
        try {
            BasicFileAttributes attribute = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            FileTime date = attribute.lastAccessTime();
            return PATTERN.parse(PATTERN.format(date.toMillis()));
        } catch (IOException e) {
            e.getMessage();
            return null;
        }catch (ParseException e){
            e.getMessage();
            return  null ;
        }
    }

    public Date creationTime(File file){
        try {
            BasicFileAttributes attribute = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            FileTime date = attribute.creationTime();
            return PATTERN.parse(PATTERN.format(date.toMillis()));
        } catch (IOException e) {
            e.getMessage();
            return null;
        }catch (ParseException e){
            e.getMessage();
            return  null ;
        }
    }
}
