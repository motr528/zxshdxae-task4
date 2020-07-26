package com.epam.rd.java.basic.practice4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.security.SecureRandom;


public class Part2 {

    private static final Logger logger = LogManager.getLogger(Part2.class);

    private static final String WIN_CHARSET = "windows-1251";
    private static final String FILE_TO_WRITE = "part2.txt";
    private static final String FILE_TO_WRITE_SORTED = "part2_sorted.txt";


    public static void main(String[] args) {

        String s = createNumbers();
        System.out.println("input ==> " + s);
        writeToFile(FILE_TO_WRITE, s);
        s = readAndWriteToFile(FILE_TO_WRITE, FILE_TO_WRITE_SORTED);
        System.out.println("output ==> " + s);

    }

    public static String createNumbers() {
        StringBuilder sb = new StringBuilder();
        SecureRandom rnd = new SecureRandom();

        for (int i = 0; i < 10; i++) {
            sb.append(rnd.nextInt(51));
            if (i < 9) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static void writeToFile(String fileName, String lineToWrite) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), WIN_CHARSET))) {
            bw.write(lineToWrite);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public static String readAndWriteToFile(String fileToRead, String fileToWrite) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileToRead), WIN_CHARSET))) {
            String s;
            StringBuilder sb = new StringBuilder();



            while ((s = br.readLine()) != null) {
                sb.append(s);
            }


            String[] strArr = sb.toString().split(" ");

            int[] arr = parseInts(strArr);

            int n = arr.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }

            sb = new StringBuilder();
            for (Integer x : arr) {
                sb.append(x).append(" ");
            }

            sb.deleteCharAt(sb.length() - 1);

            writeToFile(fileToWrite, sb.toString());

            return sb.toString();
        } catch (IOException e) {
            logger.error(e);
            return null;
        }
    }

    private static int[] parseInts(String[] numbers) {
        int[] arr = new int[numbers.length];
        try {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(numbers[i]);
            }
        } catch (NumberFormatException e) {
            logger.error(e);
        }
        return arr;
    }
}
