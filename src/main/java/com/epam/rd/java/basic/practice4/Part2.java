package com.epam.rd.java.basic.practice4;

import java.io.*;
import java.util.List;

public class Part2 {

    private final static String WIN_CHARSET = "windows-1251";
    private final static String FILE_TO_WRITE = "part2.txt";
    private final static String FILE_TO_WRITE_SORTED = "part2_sorted.txt";


    public static void main(String[] args) {

        String s = createNumbers();
        System.out.println("input ==> " + s);
        writeToFile(FILE_TO_WRITE, s);
        s = readAndWriteToFile(FILE_TO_WRITE, FILE_TO_WRITE_SORTED);
        System.out.println("output ==> " + s);

    }

    public static String createNumbers() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            sb.append((int) (Math.random() * 50));
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
            e.printStackTrace();
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
            int[] arr = new int[strArr.length];

            try {
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = Integer.parseInt(strArr[i]);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

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
            e.printStackTrace();
            return null;
        }
    }
}
