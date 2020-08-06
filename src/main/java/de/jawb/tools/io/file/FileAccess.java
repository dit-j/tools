package de.jawb.tools.io.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

import de.jawb.tools.io.net.NetworkUtil;

/**
 * @author dit (04.05.2012)
 */
public class FileAccess {

    /**
     * @param file
     * @return
     * @throws IOException
     */
    public static List<String> getContentByLine(File file) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        List<String> content = new ArrayList<String>();
        String line;
        while ((line = br.readLine()) != null) {
            content.add(line);
        }
        br.close();

        return content;
    }

    /**
     * @param file
     * @return
     * @throws IOException
     */
    public static String getContent(File file) throws IOException {
        return NetworkUtil.read(new FileInputStream(file));
    }

    /**
     * @param targetFile
     * @param content
     * @param append
     * @throws IOException
     */
    public static void writeString(File targetFile, String content, boolean append) throws IOException {
        BufferedWriter wr = _createWriter(targetFile, append);
        wr.write(content);
        wr.newLine();
        wr.close();
    }

    /**
     * @param targetFile
     * @param content
     * @param append
     * @throws IOException
     */
    public static void writeContent(File targetFile, List<String> content, boolean append) throws IOException {
        BufferedWriter wr = _createWriter(targetFile, append);
        for (String line : content) {
            wr.write(line);
            wr.newLine();
        }
        wr.close();
    }

    private static BufferedWriter _createWriter(File targetFile, boolean append) throws UnsupportedEncodingException, FileNotFoundException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile, append), "UTF-8"));
        return out;
    }

    public static void main(String[] args) throws Exception{

        String file = "/home/dikraus/pw.txt";

        List<String> lines = FileAccess.getContentByLine(new File(file));

        Set<String> passwords = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        for(String line : lines){

            String[] split = line.split(" ");
            for(int i = 1; i < split.length; i++){

                String s = split[i].trim();

                if(s.length() > 0){
                    passwords.add(s);
                }

            }

        }

        String[] s2 = {  "password", "password1", "123456789", "12345678", "1234567", "123456",
                "12345", "1234", "123", "qwerty", "abc123", "football", "monkey", "letmein",
                "111111", "1q2w3e4r", "google", "1q2w3e4r5t", "123qwe", "zxcvbnm",
                "1q2w3e", "666666", "123321", "suzuki", "yamaha", "honda"};

        passwords.addAll(Arrays.asList(s2));

        int i = 0;
        for(String ps : passwords){
            System.out.print("\""+ps+"\", ");

            if(i++ > 4){
                System.out.println();
                i=0;
            }

        }

    }
}
