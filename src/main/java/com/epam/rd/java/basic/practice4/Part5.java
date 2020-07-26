package com.epam.rd.java.basic.practice4;

import java.io.*;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part5 {

    private static final Logger logger = Logger.getLogger(Part5.class.getName());
    private static final String EXCEPTION_OCCURRED = "Exception occur";

    public static void main(String[] args) {

        getLocaleAndKey();
    }

    public static void getLocaleAndKey() {

        Locale.setDefault(Locale.ENGLISH);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String s;


            while (true) {
                s = br.readLine();

                if (s.equals("stop")) {
                    break;
                }

                String key = s.split(" ")[0];
                String language = s.split(" ")[1];

                if ("ru".equals(language)) {
                    Locale.setDefault(new Locale("ru"));
                    System.out.println(getBundleAndPrintKey(key));
                } else if ("en".equals(language)) {
                    Locale.setDefault(new Locale("en"));
                    System.out.println(getBundleAndPrintKey(key));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, EXCEPTION_OCCURRED, e);
        }
    }

    public static String getBundleAndPrintKey(String key) {
        try {

            ResourceBundle bundle = ResourceBundle.getBundle("resources");
            return bundle.getString(key);

        } catch (NullPointerException | MissingResourceException | ClassCastException e) {
            logger.log(Level.SEVERE, EXCEPTION_OCCURRED, e);
            return "kek";
        }

    }

}
