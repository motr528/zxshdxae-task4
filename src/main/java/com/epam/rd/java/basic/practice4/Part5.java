package com.epam.rd.java.basic.practice4;

import java.io.*;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Part5 {


    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(getLocaleAndKey());
    }

    public static String getLocaleAndKey() throws UnsupportedEncodingException {

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
                        break;
                    case "en":
                        locale = new Locale("en");
                        break;
                    default:
                        locale = Locale.getDefault();
                        break;
                }

                try {
                    bundle = ResourceBundle.getBundle("resources", locale);
                    return (bundle.getString(s.split(" ")[1]));

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
