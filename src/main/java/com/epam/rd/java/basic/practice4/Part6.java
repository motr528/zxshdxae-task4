package com.epam.rd.java.basic.practice4;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {

    private static final Logger logger = Logger.getLogger(Part6.class.getName());
    private static final String EXCEPTION_OCCURRED = "Exception occur";

    private static final String FILE_NAME = "part6.txt";
    private static final String WIN_CHARSET = "windows-1251";
    private static final String LINE_SEP = System.lineSeparator();


    public static void main(String[] args) {
        getSymbols();
    }

    public static void getSymbols() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;

        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            logger.log(Level.SEVERE, EXCEPTION_OCCURRED, e);
        }

        try {
            while (true) {
                s = br.readLine();

                if (s.equals("stop")) {
                    break;
                }
                System.out.print(s + ": " + getMatchingData(s.toLowerCase()));
            }
        } catch (IOException e) {
            System.out.println("Incorrect input");
            getSymbols();
        }
    }

    public static String getMatchingData(String type) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME), WIN_CHARSET))) {
            String s;
            StringBuilder sb = new StringBuilder();


            while ((s = br.readLine()) != null) {
                sb.append(s);
                if (br.ready()) {
                    sb.append(LINE_SEP);
                }
            }

            s = sb.toString();

            String regex;

            switch (type) {
                case "cyrl":
                    regex = "[\\u0400-\\u04FF]+";
                    break;
                case "latn":
                    regex = "\\w+";
                    break;
                default:
                    return "Incorrect input" + LINE_SEP;
            }

            Matcher matcher = Pattern.compile(regex).matcher(s);
            sb = new StringBuilder();

            while (matcher.find()) {
                sb.append(matcher.group()).append(" ");
            }

            sb.append(LINE_SEP);
            return sb.toString();

        } catch (IOException e) {
            logger.log(Level.SEVERE, EXCEPTION_OCCURRED, e);
        }
        return null;
    }
}
