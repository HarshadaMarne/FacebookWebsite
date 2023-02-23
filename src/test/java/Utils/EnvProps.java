package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//private constructor + static methods to create object with if block -> singleton design pattern
public class EnvProps {
    static Properties properties;
    private EnvProps(){

    }
    public static void init()
    {
        if(properties == null) {
            properties = new Properties();
            try {
                properties.load(new FileInputStream("src/test/resources/env.properties"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String getValue(String key)
    {
        init();
        return properties.getProperty(key);
    }
}
