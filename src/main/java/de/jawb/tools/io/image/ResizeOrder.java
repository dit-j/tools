package de.jawb.tools.io.image;

import java.awt.Rectangle;
import java.io.File;

/**
 * Verkleinerungsauftrag
 * 
 * @author dit (18.05.2013)
 */
public class ResizeOrder {
    
    private final File      src;
    private final File      target;
    private final int       height;
    private final Rectangle cropRectangle;
                            
    /**
     * Konstruktor.
     * 
     * @param src
     *            Quellbild
     * @param target
     *            Zielbild
     * @param newHeight
     *            gewuenschte Hoehe
     */
    public ResizeOrder(File src, File target, int newHeight) {
        super();
        this.src = src;
        this.target = target;
        this.height = newHeight;
        this.cropRectangle = null;
    }
    
    /**
     * Konstruktor.
     * 
     * @param src
     *            Quellbild
     * @param target
     *            Zielbild
     * @param newHeight
     *            gewuenschte Hoehe
     * @param rect
     *            Viereck zum Ausschneiden
     */
    public ResizeOrder(File src, File target, int newHeight, Rectangle cropRect) {
        super();
        this.src = src;
        this.target = target;
        this.height = newHeight;
        this.cropRectangle = cropRect;
    }
    
    public final File getSrc() {
        return src;
    }
    
    public final File getTarget() {
        return target;
    }
    
    public final int getHeight() {
        return height;
    }
    
    public Rectangle getCropRectangle() {
        return cropRectangle;
    }
    
    @Override
    public String toString() {
        return "ResizeOrder [src=" + src + ", target=" + target + ", height=" + height + ", cropRectangle=" + cropRectangle + "]";
    }
    
}
