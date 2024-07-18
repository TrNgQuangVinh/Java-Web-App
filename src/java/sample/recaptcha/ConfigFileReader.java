/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.recaptcha;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author KuroZabulus
 */
public class ConfigFileReader {

    private static Properties properties;
    private String fname;
    
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
    
    public ConfigFileReader(String fname) {
        File ConfigFile = new File(fname);
        try {
            FileInputStream configFileReader = new FileInputStream(ConfigFile);
            properties = new Properties();
            try {
                properties.load(configFileReader);
                configFileReader.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("config.properties not found at config file path" + fname);
        }
    }

    public String getSecretKey() {
        String key = properties.getProperty("RECAPTCHA_SECRET");
        if (key != null) {
            return key;
        } else {
            throw new RuntimeException("Key not specified in the config.properties file.");
        }
    }
}
