package com.epam.rd.java.basic.practice4;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Part5 {

    public static void main(String[] args) {

        getLocaleAndKey();
    }

    public static void getLocaleAndKey() {

        Locale.setDefault(Locale.ENGLISH);

        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.toString()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String s;

            ResourceBundle bundle = null;

            while (true) {
                s = br.readLine();

                if (s.equals("stop")) {
                    break;
                }

                switch (s) {
                    case "ru":
                        Locale.setDefault(new Locale("ru"));
                        s = br.readLine();
                        if (s.equals("stop")) {
                            return;
                        }
                        System.out.println(getBundleAndPrintKey(s, bundle));
                        break;
                    case "en":
                        Locale.setDefault(new Locale("en"));
                        s = br.readLine();
                        if (s.equals("stop")) {
                            return;
                        }
                        System.out.println(getBundleAndPrintKey(s, bundle));
                        break;
                    default:
                        System.out.println(getBundleAndPrintKey(s, bundle));
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBundleAndPrintKey(String key, ResourceBundle bundle) {
        try {

            bundle = ResourceBundle.getBundle("resources");
            return bundle.getString(key);

        } catch (NullPointerException | MissingResourceException | ClassCastException e) {
            e.printStackTrace();
            return null;
        }

    }

}
