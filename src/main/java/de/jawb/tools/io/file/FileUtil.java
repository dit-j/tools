/**
 * 
 */
package de.jawb.tools.io.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.filechooser.FileSystemView;

import de.jawb.tools.security.Generator;

/**
 * @author dit (25.07.2011)
 */
public class FileUtil {
    
//    public static final long KB = 1024;
//    public static final long MB = KB * KB;
//    public static final long GB = KB * MB;
                                
    /**
     * @param dir
     * @param fileType
     *            z.B. .png oder txt
     * @return
     * @throws IOException 
     */
    public static File createRandomNamedFile(File dir, String fileType) throws IOException { 
        
        ensureDirExists(dir);
        
        File file       = null;
        int i           = 0;
        int nameLength  = 10;
        final String fileTYPE = fileType.startsWith(".") ? fileType : ("." + fileType);
        do {
            //
            if (++i == 10) {
                i = 0;
                ++nameLength;
            }
            //
            file = new File(dir, Generator.generateToken(nameLength) + fileTYPE);
        } while (file.exists());
        
        return file;
    }
    
    /**
     * @param root
     * @param fileType
     *            z.B. .png oder .txt 
     * @return
     * @throws IOException 
     */
    public static File createRandomNamedFile(String path, String fileType) throws IOException {
        return createRandomNamedFile(new File(path), fileType);
    }
    
    /**
     * Erstellt eine Datei mit bestimmten Inhalt.
     * 
     * @param path
     *            Pfad
     * @param content
     *            Inhalt der Datei
     * @return FileObjekt
     * @throws IOException
     *             Schrebe-/Lesefehler
     */
    public static File createASCIIFile(String path, String content) throws IOException {
        File tempFile = new File(path);
        if (tempFile.createNewFile()) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
            bw.write(content);
            bw.close();
            return tempFile;
        }
        return null;
    }
    
    /**
     * Prueft ob es um ein Rootverzeichnis (c:\\, d:\\ usw.) handelt
     * 
     * @param file
     *            Verzeichnis
     * @return <code>true</code> wenn 'file' ein Rootverzeichnis ist
     */
    public static final boolean isRoot(File file) {
        return FileSystemView.getFileSystemView().isFileSystemRoot(file);
    }
    
    /**
     * Stellt sicher dass ein Verzeichnis existiert.
     * 
     * @param file
     *            Verzeichnis
     * @throws IOException
     */
    public static void ensureDirExists(File file) throws IOException {
        if (!file.exists() || !file.isDirectory()) {
            if (!file.mkdirs()) {
                throw new IOException("Directory '" + file.getAbsolutePath() + "' could not be created");
            }
        }
    }
    
    /**
     * Prueft ob eine Datei existiert.
     * 
     * @param path
     *            Pfad zur Datei
     * @return <code>true</code> wenn Datei existiert
     */
    public static final boolean fileExists(String path) {
        File file = new File(path);
        return file.exists();
    }
    
    /**
     * Prueft ob es sich um einen gueltigen Dateinamen handelt.<br>
     * <b>Wichtig:</b> Leerzeichen ist kein gueltiges Zeichen.
     * 
     * @param name
     *            der zu pruefende Name einer Datei.
     * @return <code>true</code> wenn Name gueltig ist. sonst <code>false</code>
     */
    public static boolean isValideFileName(String name) {
        char[] chars = name.toCharArray();
        if (chars.length == 0) {
            return false;
        }
        for (char ch : chars) {
            if (!Character.isLetterOrDigit(ch)) {// A-Za-z0-9
                if ((ch != '-') && (ch != '.') && (ch != '_')) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Entfernt Datei oder Verzeichnis komplett mit Inhalt (rekursiv)
     * 
     * @param file
     *            Verzeichnis oder Datei
     * @throws IOException
     */
    public static void removeAll(File file) throws IOException {
        if (!file.exists()) {
            return;
        }
        
        if (file.isDirectory()) {
            if (!file.delete()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    removeAll(f);
                }
                removeAll(file);
            }
        } else {
            if (!file.delete()) {
                throw new IOException("File '" + file.getName() + "' cannot be deleted");
            }
        }
    }
    
}
