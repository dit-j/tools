package de.jawb.tools.iso.i18n;

import java.util.Comparator;

/**
 * @author dit (25.07.2015)
 */
public class ComparatorEnglish implements Comparator<ISOPair> {
    
    @Override
    public int compare(ISOPair o1, ISOPair o2) {
        String n1 = o1.getName();
        String n2 = o2.getName();
        return n1.compareTo(n2);
    }
    
}
