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

        System.setIn(new ByteArrayInputStream(
                "table ru^table en^apple ru^stop".replace("^", System.lineSeparator()).getBytes(StandardCharsets.UTF_8)));

//        try {
//            System.setOut(new PrintStream(System.out, true, "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String s;

            ResourceBundle bundle = null;

            while (true) {
                s = br.readLine();

                if (s.equals("stop")) {
                    break;
                }

                String key = s.split(" ")[0];
                String language = s.split(" ")[1];

                switch (language) {
                    case "ru":
                        Locale.setDefault(new Locale("ru"));
                        System.out.println(getBundleAndPrintKey(key, bundle));
                        break;
                    case "en":
                        Locale.setDefault(new Locale("en"));
                        System.out.println(getBundleAndPrintKey(key, bundle));
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
            return "kek";
        }

    }

}
