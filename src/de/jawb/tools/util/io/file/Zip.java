/**
 * 
 */
package de.jawb.tools.util.io.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @author dit (29.09.2011)
 */
public class Zip {
    
    private static final int    BUFF_SIZE = 16384;
    private static final String SEP       = System.getProperty("file.separator");
    
    /**
     * @param target
     *            Ort in pk3-Datei.
     * @param src
     *            Datei
     * @param out
     *            ZipOutputStream, zipt die Datei
     * @throws IOException
     *             Bei Fehlern.
     */
    private static void writeFile(File src, ZipOutputStream out) throws IOException {
        BufferedInputStream in = null;
        
        String path = src.getName();
        
        ZipEntry entry = new ZipEntry(path);
        
        out.putNextEntry(entry);
        try {
            BufferedOutputStream bOut = new BufferedOutputStream(out);
            in = new BufferedInputStream(new FileInputStream(src));
            
            byte[] buf = new byte[BUFF_SIZE];
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                bOut.write(buf, 0, len);
            }
            bOut.flush();
        } finally {
            if (in != null) {
                in.close();
            }
            out.closeEntry();
        }
    }
    
    /**
     * Erzeugt eine zip-Datei im gleichem Verzeichnis wie das Original.
     * 
     * @param source
     *            quelle, ungezippte Datei.
     * @param zipFileName
     *            Name der Zipdatei ohne Erweiterung (.zip wird angehaengt)
     * @return Referenz auf die gezippte Datei
     * @throws IOException
     *             Bei Fehlern.
     */
    public static final File zip(File source, String zipFileName) throws IOException {
        return zipAs(source, zipFileName, "zip");
    }
    
    /**
     * Erzeugt eine zip-Datei im gleichem Verzeichnis wie das Original.
     * 
     * @param source
     *            Quelle, ungezippte Datei
     * @param newFileName
     *            Name der gezippten Datei
     * @param fileType
     *            Erweiterung, Dateityp
     * @return gezippte Datei bzw. Verzeichnis
     * @throws IOException
     *             Fehler beim Lesen oder Schreiben der Datei
     */
    public static final File zipAs(File source, String newFileName, String fileType)
            throws IOException {
        String pathname = source.getParentFile().getAbsolutePath() + SEP + newFileName + "."
                + fileType;
        File zippedFile = new File(pathname);
        zippedFile.createNewFile();
        
        ZipOutputStream os = new ZipOutputStream(new FileOutputStream(zippedFile));
        writeFile(source, os);
        os.close();
        return zippedFile;
    }
    
    private static File buildDirectoryHierarchyFor(String entryName, File destDir) {
        int lastIndex = entryName.lastIndexOf('/');
        String internalPathToEntry = entryName.substring(0, lastIndex + 1);
        return new File(destDir, internalPathToEntry);
    }
    
    /**
     * Entpackt nur bestimmte Dateien die in ihrem Namen einen String enthalten.
     * 
     * @param zipped
     *            Archivierte Datei
     * @param destDir
     *            Das Zielverzeichnis
     * @param fileType
     *            String den eine Datei enthalten soll um entpackt zu werden.
     * @throws ZipException
     *             Fehler im Zip-Archiv
     * @throws IOException
     *             LeseFehler
     */
    public static void unzipSpecificFile(File zipped, File destDir, String fileType)
            throws ZipException, IOException {
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        
        ZipFile zipFile = new ZipFile(zipped);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        
        byte[] buffer = new byte[BUFF_SIZE];
        int len;
        while (entries.hasMoreElements()) {
            
            ZipEntry entry = entries.nextElement();
            String entryFileName = entry.getName();
            
            if ((fileType != null) && !entryFileName.contains(fileType)) {
                //UEBERSPRINGE
                continue;
            }
            
            File dir = buildDirectoryHierarchyFor(entryFileName, destDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            if (!entry.isDirectory()) {
                File file = new File(destDir, entryFileName);
                FileOutputStream out = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(out);
                
                InputStream inputStream = zipFile.getInputStream(entry);
                BufferedInputStream bis = new BufferedInputStream(inputStream);
                
                //DATEN ENTPACKEN
                while ((len = bis.read(buffer)) > 0) {
                    bos.write(buffer, 0, len);
                }
                
                bos.flush();
                bos.close();
                bis.close();
            }
        }
        zipFile.close();
    }
    
    /**
     * Entpackt den ganzen Inhalt einer Datei in ein bestimmtes Verzeichnis
     * 
     * @param zipped
     *            archivierte Datei
     * @param destDir
     *            Zielverzeichnis
     * @throws ZipException
     *             Fehler im Zip-Archiv
     * @throws IOException
     *             Lesefehler
     */
    public static void unzip(File zipped, File destDir) throws ZipException, IOException {
        unzipSpecificFile(zipped, destDir, null);
    }
    
    public static void zipDir(File src, String target) throws IOException{
        writeFilesRecursive(target, src, new ZipOutputStream(new FileOutputStream(target)));
    }
    
    private static void writeFilesRecursive(String target, File src, ZipOutputStream out) throws IOException {
        if (src.isDirectory()) {
            File[] files = src.listFiles();
            if (files != null) {
                for (File file : files) {
                    writeFilesRecursive(target, file, out);
                }
            }
        } else {
            writeFile(target, src, out);
        }
    }
    
    private static void writeFile(String target, File src, ZipOutputStream out) throws IOException {
        BufferedInputStream in = null;
        
        String path = null;
        
        if (target.length() == 0) {
            path = src.getName();
        } else {
            path = target + File.separatorChar + src.getName();
        }
        
        ZipEntry entry = new ZipEntry(path);
        
        out.putNextEntry(entry);
        try {
            BufferedOutputStream bOut = new BufferedOutputStream(out);
            in = new BufferedInputStream(new FileInputStream(src));
            
            byte[] buf = new byte[1048576];
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                bOut.write(buf, 0, len);
            }
            bOut.flush();
        } finally {
            if (in != null) {
                in.close();
            }
            out.closeEntry();
        }
    }
    
    public static void main(String[] args) throws IOException {
//        File f = new File("D:\\Spiele\\Wolfenstein - Enemy Territory\\etmain");
//        File[] files = f.listFiles();
//        File d = new File("c:\\tmp");
//        long s = System.currentTimeMillis();
//        for (File file : files) {
//            String name = file.getName();
//            if (name.endsWith(".pk3")) {
//                System.out.println("Entpacke " + name);
//                Zip.unzipSpecificFile(file, d, ".arena");
//            }
//        }
//        long e = System.currentTimeMillis();
//
//        System.out.println("DONE IN=" + (e - s));
        
        Zip.zipDir(new File("d:\\1"), "d:\\test.zip");
        
    }
}
