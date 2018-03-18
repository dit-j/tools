package de.jawb.tools.date;

import java.text.DateFormat;
import java.util.Locale;

public enum Style {
    
    DateTime {
        @Override
        DateFormat getDateFormat(Locale l) {
            return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, l);
        }
    },
    Date {
        @Override
        DateFormat getDateFormat(Locale l) {
            return DateFormat.getDateInstance(DateFormat.MEDIUM, l);
        }
    },
    Time {
        @Override
        DateFormat getDateFormat(Locale l) {
            return DateFormat.getTimeInstance(DateFormat.SHORT, l);
        }
    };
    
    abstract DateFormat getDateFormat(Locale l);
}
