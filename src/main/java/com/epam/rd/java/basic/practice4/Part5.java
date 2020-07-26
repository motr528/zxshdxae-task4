package com.epam.rd.java.basic.practice4;

import java.io.*;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Part5 {

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "windows-1251"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        getLocaleAndKey();
    }

    public static void getLocaleAndKey() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String s;

            ResourceBundle bundle;
            Locale locale;

            while (true) {
                s = br.readLine();

                if (s.equals("stop")) {
                    break;
                }

                if (s.equals("ru")) {
                    locale = new Locale("ru");
                    Locale.setDefault(locale);
                    s = br.readLine();
                } else if (s.equals("en")) {
                    locale = new Locale("en");
                    Locale.setDefault(locale);
                    s = br.readLine();
                }

//                switch (s) {
//                    case "ru":
//                        locale = new Locale("ru");
//                        break;
//                    case "en":
//                        locale = new Locale("en");
//                        break;
//                    default:
//                        throw new IllegalStateException("Unexpected value: " + s);
//                }


                try {

                    bundle = ResourceBundle.getBundle("resources");
                    System.out.println(bundle.getString(s));

                } catch (NullPointerException | MissingResourceException | ClassCastException e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
