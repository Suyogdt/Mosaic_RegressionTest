package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

    public static Properties properties;
    public static Properties Marketplaceproperties;

    public static Properties readProperty() {

        try {
            FileInputStream in = new FileInputStream("config.properties");


            properties = new Properties();


            properties.load(in);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return properties;


    }
}
