package de.jawb.tools.io.file;

import java.io.IOException;

/**
 * Speichert und Laedt JavaObjekte.
 *
 * @author dit (08.05.2011)
 */
@Deprecated
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
        ObjectSerialization.serialize(obj, saveAs);
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
    public static Object read(String path) throws IOException, ClassNotFoundException {
        return ObjectSerialization.deserialize(path);
    }
}
