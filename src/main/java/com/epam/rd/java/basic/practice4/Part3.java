package com.epam.rd.java.basic.practice4;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    private static final String FILE_NAME = "part3.txt";
    private static final String WIN_CHARSET = "windows-1251";
    private static final String LINE_SEP = System.lineSeparator();


    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            getType();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void getType() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = "";

        try {
            while (true) {
                s = br.readLine();
                System.out.println(getMatchingData(s));
                if (s.equals("stop")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            getType();
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

            String regex = "";

            switch (type) {
                case "char":
                    regex = "\\b[\\w\\p{L}]{1}\\b(?=\\s)";
                    break;
                case "int":
                    regex = "(?<!\\w|\\.)\\d+(?=\\s)";
                    break;
                case "double":
                    regex = "(\\d+)?\\.\\d+";
                    break;
                case "String":
                    regex = "[\\w\\p{L}&&\\D]{2,}";
                    break;
            }

            Matcher matcher = Pattern.compile(regex).matcher(s);
            sb = new StringBuilder();

            while (matcher.find()) {
                sb.append(matcher.group()).append(" ");
            }
            if (sb.length() != 0) {
                sb.deleteCharAt(sb.length() - 1);
            }

            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
