package de.jawb.tools.io.file;

import java.io.*;

/**
 * Speichert und Laedt JavaObjekte.
 *
 * @author dit (08.05.2011)
 */
public class ObjectSerialization {

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
    public static void serialize(Object obj, String saveAs) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saveAs))) {
            out.writeObject(obj);
        }
    }

    /**
     * Erstellt ein Objekt aus einer Datei durch die Deserialisierung
     *
     * @param path
     *            Pfad zur Datei.
     * @return Deserialisierte Datei
     * @throws IOException
     *             Schreibe-/Lesefehler
     * @throws ClassNotFoundException
     *             Klasse wurde nicht gefunden.
     */
    public static Object deserialize(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            return in.readObject();
        }
    }
}
