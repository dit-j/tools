package de.jawb.tools.io.image;

import java.io.IOException;

/**
 * @author dit (18.05.2013)
 */
public enum ImageType {
    
    png(
            ".png"),
    jpg(
            ".jpg"),
    jpeg(
            ".jpeg"),
    gif(
            ".gif"),
    bmp(
            ".bmp");
            
    public final String end;
    
    private ImageType(String e) {
        end = e;
    }
    
    public static final ImageType getImageTypeByFileName(String name) throws IOException {
        final String fileName = name.toLowerCase();
        for (ImageType t : ImageType.values()) {
            if (fileName.endsWith(t.end)) {
                return t;
            }
        }
        throw new IOException("Unsupported image type '" + name + "'");
    }
    
}
