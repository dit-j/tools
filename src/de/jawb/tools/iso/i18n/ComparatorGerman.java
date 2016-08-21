package de.jawb.tools.iso.i18n;

import java.util.Comparator;

/**
 * @author dit (25.07.2015)
 */
public class ComparatorGerman implements Comparator<ISOPair> {
    
    @Override
    public int compare(ISOPair o1, ISOPair o2) {
        String n1 = o1.getName().replaceFirst("�", "Ae").replaceFirst("�", "Oe");
        String n2 = o2.getName().replaceFirst("�", "Ae").replaceFirst("�", "Oe");
        return n1.compareTo(n2);
    }
    
}
