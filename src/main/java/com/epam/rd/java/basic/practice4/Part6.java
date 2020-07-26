package com.epam.rd.java.basic.practice4;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {
    private static final String FILE_NAME = "part6.txt";
    private static final String WIN_CHARSET = "windows-1251";
    private static final String LINE_SEP = System.lineSeparator();


    public static void main(String[] args) {
        getSymbols();
    }

    public static void getSymbols() {
        System.setIn(new ByteArrayInputStream(
                "Latn^cyrl^asdf^stop".replace("^", System.lineSeparator()).getBytes(StandardCharsets.UTF_8)));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;

        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
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
//            if (sb.length() != 0) {
//                sb.deleteCharAt(sb.length() - 1);
//            }
            sb.append(LINE_SEP);
            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
