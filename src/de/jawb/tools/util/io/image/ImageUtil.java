package de.jawb.tools.util.io.image;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * @author dit (18.05.2013)
 */
public class ImageUtil {
    
    public static final ImageDimension getImageSize(File imgFile) throws IOException {
        try (ImageInputStream in = ImageIO.createImageInputStream(imgFile)) {
            final Iterator<ImageReader> readers = ImageIO.getImageReaders(in);
            if (readers.hasNext()) {
                ImageReader reader = readers.next();
                try {
                    reader.setInput(in);
                    return new ImageDimension(reader.getWidth(0), reader.getHeight(0));
                } finally {
                    reader.dispose();
                }
            }
        }
        throw new IOException("unknown Error");
    }
    
    private static final int calculateWidth(int oldWidth, int oldHeight, int newHeight) {
        double r = oldWidth / (double) oldHeight;
        return (int) (r * newHeight);
    }
    
    /**
     * @param srcFile
     * @param rect
     * @return
     * @throws IOException
     */
    public static void cropImage(File srcFile, Rectangle rect, File target) throws IOException {
        
        ImageType type = ImageType.getImageTypeByFileName(srcFile.getName());
        BufferedImage img = cropImage(ImageIO.read(srcFile), rect);
        boolean success = ImageIO.write(img, type.toString(), target);
        
        if (!success) throw new IOException("img could not be stored");
    }
    
    public static BufferedImage cropImage(BufferedImage src, Rectangle rect) {
        BufferedImage dest = src.getSubimage(rect.x, rect.y, rect.width, rect.height);
        return dest;
    }
    
    /**
     * Verkleinert ein Bild zu einer bestimmten Groesse. Proportionen werden
     * dabei beibehalten.
     * 
     * @param image
     *            Quelle
     * @param newHeight
     *            gewuenschte Hoehe
     * @return Bild als {@link BufferedImage}
     */
    public static BufferedImage resizeImage(final Image image, int newHeight) {
        
        int oldW = image.getWidth(null);
        int oldH = image.getHeight(null);
        int newW = calculateWidth(oldW, oldH, newHeight);
        
        BufferedImage bufferedImage = new BufferedImage(newW, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, newW, newHeight, null);
        graphics2D.dispose();
        
        return bufferedImage;
    }
    
    /**
     * @param order
     * @throws IOException
     */
    public static void resizeImage(ResizeOrder order) throws IOException {
        
        BufferedImage img = ImageIO.read(order.getSrc());
        
        if (order.getCropRectangle() != null) {
            img = cropImage(img, order.getCropRectangle());
        }
        
        ImageType type = ImageType.getImageTypeByFileName(order.getSrc().getName());
        BufferedImage finalImg = img.getHeight() > order.getHeight() ? resizeImage(img, order.getHeight()) : img;
        
        boolean success = ImageIO.write(finalImg, type.toString(), order.getTarget());
        if (!success) throw new IOException("img could not be stored");
    }
    
    /**
     * @param orders
     * @throws IOException
     */
    public static void resizeImages(List<ResizeOrder> orders) throws IOException {
        for (ResizeOrder resizeOrder : orders) {
            resizeImage(resizeOrder);
        }
    }
    
//    public static void main(String[] args) throws IOException {
//        File f1 = new File("d:\\c.jpg");
//        File f2 = new File("d:\\b.jpg");
////        ResizeOrder o = new ResizeOrder(f1, f2, 150, new Rectangle(200, 200, 400, 400));
////        resizeImage(o);
//
////        cropImage(f1, new Rectangle(200, 200, 400, 400), f2);
//        
//        long s = System.currentTimeMillis();
//        System.out.println(getImageSize(f1));
//        long e = System.currentTimeMillis();
//        
//        System.out.println(e - s);
//        
////        s = System.currentTimeMillis();
////        System.out.println(getImageSize2(f1));
////        e = System.currentTimeMillis();
////        
////        System.out.println(e - s);
//    }

}
