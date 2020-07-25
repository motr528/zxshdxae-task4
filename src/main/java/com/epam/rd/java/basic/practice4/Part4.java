package com.epam.rd.java.basic.practice4;

import java.io.*;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 implements Iterable<String> {

    private static final String FILE_NAME = "part4.txt";
    private static final String WIN_CHARSET = "windows-1251";
    private static final String LINE_SEP = System.lineSeparator();
    private static final String REGEX = "[\\w\\p{Upper}\\p{L}][^.!?]*[.!?]";

    public static void main(String[] args) {
        Part4 part4 = new Part4();
        Iterator<String> iterator = part4.iterator();


        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            sb.append(iterator.next()).append(" ");
            sb.append(LINE_SEP);
        }
        System.out.println(sb.toString().trim());
    }

    @Override
    public Iterator<String> iterator() {

        Matcher matcher = Pattern.compile(REGEX).matcher(getText());

        return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return matcher.find();
            }

            @Override
            public String next() {
                return matcher.group();
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
}
