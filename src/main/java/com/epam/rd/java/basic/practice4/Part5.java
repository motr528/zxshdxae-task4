package com.epam.rd.java.basic.practice4;

import java.io.*;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

public class Part5 {





    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(getLocaleAndKey());

    }

    public static String getLocaleAndKey() throws UnsupportedEncodingException {

        String ruPropPath = "\\src\\main\\resources\\resources_ru.properties";
        String enPropPath = "resources_en.properties";
        Properties ruProp = new Properties();
        Properties enProp = new Properties();
        Properties prop = new Properties();

        try {
            FileInputStream ruFis = new FileInputStream(ruPropPath);
            FileInputStream enFis = new FileInputStream(enPropPath);

            ruProp.load(ruFis);
            enProp.load(enFis);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String s;

            StringBuilder sb = new StringBuilder();

            ResourceBundle bundle;
            Locale locale;

            while (true) {
                s = br.readLine();

                if (s.equals("stop")) {
                    break;
                }

                switch (s.split(" ")[0]) {
                    case "ru":
                        locale = new Locale("ru");
                        prop = ruProp;
                        break;
                    case "en":
                        locale = new Locale("en");
                        prop = enProp;
                        break;
                    default:
                        locale = Locale.getDefault();
                        break;
                }

                try {
//                    bundle = ResourceBundle.getBundle("resources", locale);
//                    return (bundle.getString(s.split(" ")[1]));
                    return prop.getProperty((s.split(" ")[1]));

                } catch (NullPointerException | MissingResourceException | ClassCastException e) {
                    e.printStackTrace();
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
