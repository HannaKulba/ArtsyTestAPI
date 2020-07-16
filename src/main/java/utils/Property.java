package utils;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Property {

    public static String getPropertyValue(String propertyKey) throws IOException {
        String result = "";
        InputStream inputStream = null;

        try {
            Properties prop = new Properties();
            String propFileName = "login.properties";
            inputStream = Property.class.getClassLoader().getResourceAsStream(propFileName);
            prop.load(inputStream);
            result = prop.getProperty(propertyKey);
        } catch (Exception e) {
            Log.error(e);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return result;
    }
}
