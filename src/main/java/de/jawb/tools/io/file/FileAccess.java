package de.jawb.tools.io.file;

import de.jawb.tools.io.net.NetworkUtil;

import java.io.*;
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
}
