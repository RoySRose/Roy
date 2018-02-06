package main.java.com.navercorp.pinpoint.twitbot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.Properties;

public class Main {


    private static final String APPLICATION_CONTEXT_CONFIG = "/applicationContext.xml";

    public static void main(String[] args) {


        ApplicationContext context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_CONFIG);


        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("config.properties");

            // set the properties value
            prop.setProperty("database", "localhost");
            prop.setProperty("dbuser", "test");
            prop.setProperty("dbpassword", "password");

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}