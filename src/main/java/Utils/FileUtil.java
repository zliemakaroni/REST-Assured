package Utils;

import Configuration.Config;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    public static String encodeFileToBase64Binary(File fileName) {
        byte[] encoded = new byte[0];
        try {
            encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(encoded, StandardCharsets.UTF_8);
    }

    public static File getScreenshotFile() {
        return new File(ScreenshotUtil.getDefaultScreenshot());
    }

    public static File getLogFile() {
        return new File( FileUtil.class.getClassLoader().getResource(Config.getLogName()).toString().substring(5));
    }

    public static String getLogAsString(File logFile) {
        try {
            return Files.readString(Paths.get(logFile.getAbsolutePath()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
