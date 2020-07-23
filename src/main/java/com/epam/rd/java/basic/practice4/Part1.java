package com.epam.rd.java.basic.practice4;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static final String LINE_SEP = System.lineSeparator();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("part1.txt"), "windows-1251"))) {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            String s;
            StringBuilder sb = new StringBuilder();
            while ((s = br.readLine()) != null) {
                sb.append(s);
                if (br.ready()) {
                    sb.append(LINE_SEP);
                }

            }

            s = sb.toString();
            Matcher matcher = Pattern.compile("[\\w\\p{L}]{4,}").matcher(s);

            while (matcher.find()) {
                s = s.replace(matcher.group(), matcher.group().substring(2));
            }

            System.out.println(s);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
