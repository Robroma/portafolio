package Read;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ReadFile {
    static public FileInputStream getArxivo(String path){
        try {
            return new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
