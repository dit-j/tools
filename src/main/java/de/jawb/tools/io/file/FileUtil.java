/**
 *
 */
package de.jawb.tools.io.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.filechooser.FileSystemView;

import de.jawb.tools.collections.CollectionsUtil;
import de.jawb.tools.io.ByteUtil;
import de.jawb.tools.security.Generator;
import de.jawb.tools.string.Regex;
import de.jawb.tools.string.StringCleaner;
import de.jawb.tools.string.StringUtil;

/**
 * @author dit (25.07.2011)
 */
public class FileUtil {

    public static String getUserHomePath() {
        String property = System.getProperty("user.home");

        if(property == null){
            throw new IllegalStateException("System property 'user.home' is not available");
        }

        return property;
    }

    public static File getUserHomeFile() {
        return new File(getUserHomePath());
    }

    /**
     * @param dir
     * @param fileType
     *            z.B. .png oder txt
     * @return
     * @throws IOException
     */
    public static File createRandomNamedFile(File dir, String fileType) throws IOException {

        ensureDirExists(dir);

        File file = null;
        int i = 0;
        int nameLength = 10;
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
     * @param path
     * @param fileType
     *            z.B. .png oder .txt
     * @return
     * @throws IOException
     */
    public static File createRandomNamedFile(String path, String fileType) throws IOException {
        return createRandomNamedFile(new File(path), fileType);
    }

    /**
     * Erstellt eine Datei mit bestimmten Inhalt (UTF-8).
     *
     * @param target
     *            Pfad
     * @param content
     *            Inhalt der Datei
     * @return FileObjekt
     * @throws IOException
     *             Schrebe-/Lesefehler
     */
    public static File createFileWithContent(String target, String content) throws IOException {
        File file = new File(target);
        if (file.createNewFile()) {
            FileAccess.writeString(file, content, false);
            return file;
        }
        throw new IOException("can not create file: " + target);
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

        if (StringUtil.isEmptyTrim(name)) {
            return false;
        }

        for (char ch : name.toCharArray()) {
            if (!Character.isLetterOrDigit(ch)) {// A-Za-z0-9
                if ((ch != '-') && (ch != '.') && (ch != '_')) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @param f
     * @return
     */
    public long getSize(File f) {
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            long size = 0L;
            if (CollectionsUtil.notEmpty(files)) {
                for (File file : files) {
                    size += getSize(file);
                }
            }
            return size;
        }
        return f.length();
    }

    /**
     * @param f
     * @return
     */
    public String getReadableFileSize(File f) {
        return ByteUtil.getReadableSizeString(getSize(f));
    }

    /**
     * Entfernt alle unnoetigen Zeichen aus dem String der als KnotenName verwendet wird.
     *
     * @param string
     *            String mit FarbeCodes oder SonderZeichen.
     * @return ein String vom Typ {@link Regex#WORD}
     */
    public static String createValideFileName(String string) {
        if (StringUtil.isEmptyTrim(string)) {
            throw new IllegalArgumentException("can not be empty");
        }
        String temp = StringCleaner.removeDoubleWhiteSpaces(string).trim();
        return temp.replaceAll(Regex.WHITESPACE.regex, "-").replaceAll(Regex.NON_WORD.regex, "");
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
