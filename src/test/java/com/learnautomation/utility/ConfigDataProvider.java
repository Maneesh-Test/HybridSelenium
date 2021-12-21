package com.learnautomation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {

    Properties pro;

    public ConfigDataProvider() {
        File src = new File(System.getProperty("user.dir") +"/Config/Config.properties");
        try {
            FileInputStream fis = new FileInputStream(src);
            pro = new Properties();
            pro.load(fis);
        } catch (Exception e) {
            System.out.println("Not able to load config file >>" + e.getMessage());
        }
    }

    public String getDataFromConfig(String keyToSearch) {
        return pro.getProperty(keyToSearch);

    }

    public String getBrowser() {
        return pro.getProperty("Browser");
    }

    public String sendUsername() {
        return pro.getProperty("userName");
    }

    public String sendPassword() {
        return pro.getProperty("passWord");
    }


    public String getStagingUrl() {
        return pro.getProperty("qaURL");
    }

    public String expectedTitle() {
        return pro.getProperty("expectedTitle");
    }
}
