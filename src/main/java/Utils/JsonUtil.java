package Utils;

import Models.Test;
import aquality.selenium.core.utilities.ISettingsFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    public static String getStringFromFile(String param, ISettingsFile file) {
        return file.getValue("/" + param).toString();
    }

    public static int getIntFromFile(String param, ISettingsFile file) {
        return Integer.parseInt(getStringFromFile(param, file));
    }

    public static List<Test> createTestListFromString(String in) {
        ObjectMapper mapper = new ObjectMapper();

        CollectionType listType =
                mapper.getTypeFactory().constructCollectionType(ArrayList.class, Test.class);

        try {
            List<Test> out = mapper.readValue(in, listType);
            return out;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
