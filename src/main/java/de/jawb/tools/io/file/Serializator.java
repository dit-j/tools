/**
 * 
 */
package de.jawb.tools.io.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Speichert und Laedt JavaObjekte.
 * 
 * @author dit (08.05.2011)
 */
public class Serializator {
    
    /**
     * Speichert ein Objekt in einer Datei.
     * 
     * @param obj
     *            Serializable-Objekt.
     * @param saveAs
     *            Vollstaendiger Name der Datei
     * @throws IOException
     *             Bei Fehlern.
     */
    public static void write(Object obj, String saveAs) throws IOException {
        FileOutputStream fos = new FileOutputStream(saveAs);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(obj);
        out.close();
    }
    
    /**
     * Erstellt ein Objekt aus einer Datei durch die
     * Deserialisierung
     * 
     * @param path
     *            Pfad zur Datei.
     * @return Deserialisierte Datei
     * @throws IOException
     *             Schreibe-/Lesefehler
     * @throws ClassNotFoundException
     *             Klasse wurde nicht gefunden.
     */
    public static Object read(String path) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream in = new ObjectInputStream(fis);
        Object object = in.readObject();
        in.close();
        return object;
    }
}
