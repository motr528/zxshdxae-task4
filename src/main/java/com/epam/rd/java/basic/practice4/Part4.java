package com.epam.rd.java.basic.practice4;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 implements Iterable<String> {

    private static final String FILE_NAME = "part4.txt";
    private static final String WIN_CHARSET = "windows-1251";
    private static final String LINE_SEP = System.lineSeparator();
//    private static final String REGEX = "[\\w\\p{Upper}\\p{L}][^.!?]*[.!?]";
//    private static final String REGEX = "[\\w\\p{Upper}\\p{L}].*?[.!?]";
    private static final String REGEX = "[\\w\\p{Upper}\\p{L}][\\w\\p{L}\\s,?!]*?[.!?]";

    public static void main(String[] args) {
        Part4 part4 = new Part4();
        Iterator<String> iterator = part4.iterator();

        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuilder str = new StringBuilder();
        while (iterator.hasNext()) {

            System.out.println(iterator.next());
        }


    }

    @Override
    public Iterator<String> iterator() {

        return new Iterator<String>() {

            Matcher matcher = Pattern.compile(REGEX).matcher(getText());

            @Override
            public boolean hasNext() {
                return matcher.find();
            }

            @Override
            public String next() {
                String s = matcher.group();
                return s;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };

    }

    public static String getText() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME), WIN_CHARSET))) {
            String s;
            StringBuilder sb = new StringBuilder();

            while ((s = br.readLine()) != null) {
                sb.append(s);
            }

            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String readContent() {
        StringBuilder content = new StringBuilder();
        try (Scanner sc = new Scanner(new File("part4.txt"), "cp1251")) {
            while (sc.hasNext()) {
                content.append(sc.useDelimiter("[ " + LINE_SEP + "]").next()).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString().trim();
    }
}
