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
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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
    
    public static String getContent(File file) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        
        return sb.toString();
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
    
    private static BufferedWriter _createWriter(File targetFile, boolean append) throws UnsupportedEncodingException, FileNotFoundException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile, append), "UTF-8"));
        return out;
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
    
    public static void main(String[] args) throws IOException {
        
        File file = new File("d:\\t.xml");
        File file2 = new File("d:\\lang.properties");
        file2.delete();
        file2 = new File("d:\\lang.properties");
        file2.createNewFile();
        List<String> lines = getContentByLine(file);
        
        for (String l : lines) {
            String line = l.trim();
            if (line.startsWith("<td xml:lang")) {
                // System.out.println(line);
                try {
                    int i = line.indexOf(" lang=");
                    int j = line.indexOf("</td>");
                    
                    String lang = line.substring(i + 7, i + 9);
                    String name = line.substring(i + 11, j);
                    // System.out.println(lang);
                    System.out.println(new String(("lang." + lang + "=" + name).getBytes(), Charset.forName("UTF-8")));
//					writeString(file2, new String(("lang." + lang + "=" + name).getBytes(), Charset.forName("UTF-8")), true);
                } catch (Exception e) {
                    System.err.println(line);
                }
            }
        }
        
    }
}
