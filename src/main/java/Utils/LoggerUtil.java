package Utils;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class LoggerUtil {
    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);
    private static final File appenderFile = FileUtil.getLogFile();

    public static Logger getInstance() {
        return logger;
    }

    private static Appender appener = null;

    public static void addMessagesAppender(){
        Appender appender = getAppenderInstance();
        appender.start();
        LoggerContext.getContext(false).getRootLogger().addAppender(appender);
        try {
            System.setOut(new PrintStream(new FileOutputStream(appenderFile.getAbsolutePath())));
        } catch (FileNotFoundException e) {
            LoggerUtil.getInstance().error(String.format("Didn't find file [%s]", appenderFile.getAbsolutePath()));
        }
        try {
            System.setErr(new PrintStream(new FileOutputStream(appenderFile.getAbsolutePath())));
        } catch (FileNotFoundException e) {
            LoggerUtil.getInstance().error(String.format("Didn't find file [%s]", appenderFile.getAbsolutePath()));
        }
    }

    public static Appender getAppenderInstance(){
        if(appener == null) {
        Layout layout = PatternLayout.newBuilder().withPattern("%m%n").build();
        appener = FileAppender.newBuilder().setName("test")
                .setLayout(layout)
                .withFileName(appenderFile.getPath())
                .withAppend(true)
                .build(); }
        return appener;
    }
}
