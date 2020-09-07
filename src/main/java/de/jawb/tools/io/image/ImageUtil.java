package de.jawb.tools.io.image;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

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

    public static final int calculateWidth(int oldWidth, int oldHeight, int newHeight) {
        double r = oldWidth / (double) oldHeight;
        return (int) (r * newHeight);
    }

    public static final int calculateHeight(int oldWidth, int oldHeight, int newWidth) {
        double r = oldHeight / (double) oldWidth;
        return (int) (r * newWidth);
    }

    /**
     * @param srcFile
     * @param rect
     * @return
     * @throws IOException
     */
    public static ImageDimension cropImage(File srcFile, Rectangle rect) throws IOException {
        return cropImage(srcFile, rect, srcFile);
    }

    public static ImageDimension cropImage(File srcFile, Rectangle rect, File target) throws IOException {

        ImageType type = ImageType.getImageTypeByFileName(srcFile.getName());
        BufferedImage img = cropImage(ImageIO.read(srcFile), rect);
        boolean success = ImageIO.write(img, type.toString(), target);

        if (!success)
            throw new IOException("img could not be stored");

        ImageDimension imageDimension = new ImageDimension(img.getWidth(), img.getHeight());

        img.getGraphics().dispose();
        img = null;

        return imageDimension;
    }

    public static BufferedImage cropImage(BufferedImage src, Rectangle rect) {
        BufferedImage dest = src.getSubimage(rect.x, rect.y, rect.width, rect.height);
        return dest;
    }

    /**
     * Verkleinert ein Bild zu einer bestimmten Groesse. Proportionen werden dabei beibehalten.
     *
     * @param image
     *            Quelle
     * @param newHeight
     *            gewuenschte Hoehe
     * @return Bild als {@link BufferedImage}
     */
    private static BufferedImage resizeImage(final BufferedImage image, ImageType type, int newHeight) {
        int oldW = image.getWidth(null);
        int oldH = image.getHeight(null);
        int newW = calculateWidth(oldW, oldH, newHeight);
        return createHeadlessSmoothBufferedImage(image, type, newW, newHeight);

        // BufferedImage bufferedImage = new BufferedImage(newW, newHeight, BufferedImage.TYPE_BYTE_INDEXED,
        // (IndexColorModel) image.getColorModel());
        // Graphics2D graphics2D = bufferedImage.createGraphics();
        // graphics2D.setComposite(AlphaComposite.Src);
        //
        // graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        // graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        // graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // graphics2D.drawImage(image, 0, 0, newW, newHeight, null);
        // graphics2D.dispose();
        //
        // return bufferedImage;
    }

    /**
     * @param order
     * @throws IOException
     */
    public static void resizeImage(ResizeOrder order) throws IOException {

        final File src = order.getSrc();
        BufferedImage img = ImageIO.read(src);

        if (order.getCropRectangle() != null) {
            img = cropImage(img, order.getCropRectangle());
        }

        ImageType type = ImageType.getImageTypeByFileName(src.getName());
        BufferedImage finalImg = img.getHeight() > order.getHeight() ? resizeImage(img, type, order.getHeight()) : img;

        boolean success = ImageIO.write(finalImg, type.toString(), order.getTarget());
        if (!success)
            throw new IOException("img could not be stored");
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

    /**
     * Creates a <code>BufferedImage</code> from an <code>Image</code>. This method can function on a completely
     * headless system. This especially includes Linux and Unix systems that do not have the X11 libraries installed,
     * which are required for the AWT subsystem to operate. The resulting image will be smoothly scaled using bilinear
     * filtering.
     * https://joinup.ec.europa.eu/svn/cosladadigital/coslada-ciudad-digital/tags/version_2_0/ccd-app/ccdjforum-war/src/main/java/net/jforum/util/image/ImageUtils.java
     *
     * @param source
     *            The image to convert
     * @param width
     *            The desired image width
     * @param height
     *            The desired image height
     * @return The converted image
     * @param type
     *            int
     */
    private static BufferedImage createHeadlessSmoothBufferedImage(BufferedImage source, ImageType type, int width, int height) {

        int bimgtype = BufferedImage.TYPE_INT_RGB;

        if (type == ImageType.png && hasAlpha(source)) {
            bimgtype = BufferedImage.TYPE_INT_ARGB;
        }

        BufferedImage dest = new BufferedImage(width, height, bimgtype);

        int sourcex;
        int sourcey;

        double scalex = (double) width / source.getWidth();
        double scaley = (double) height / source.getHeight();

        int x1;
        int y1;

        double xdiff;
        double ydiff;

        int rgb;
        int rgb1;
        int rgb2;

        for (int y = 0; y < height; y++) {
            sourcey = y * source.getHeight() / dest.getHeight();
            ydiff = scale(y, scaley) - sourcey;

            for (int x = 0; x < width; x++) {
                sourcex = x * source.getWidth() / dest.getWidth();
                xdiff = scale(x, scalex) - sourcex;

                x1 = Math.min(source.getWidth() - 1, sourcex + 1);
                y1 = Math.min(source.getHeight() - 1, sourcey + 1);

                rgb1 = getRGBInterpolation(source.getRGB(sourcex, sourcey), source.getRGB(x1, sourcey), xdiff);
                rgb2 = getRGBInterpolation(source.getRGB(sourcex, y1), source.getRGB(x1, y1), xdiff);

                rgb = getRGBInterpolation(rgb1, rgb2, ydiff);

                dest.setRGB(x, y, rgb);
            }
        }

        return dest;
    }

    private static double scale(int point, double scale) {
        return point / scale;
    }

    private static int getRGBInterpolation(int value1, int value2, double distance) {
        int alpha1 = (value1 & 0xFF000000) >>> 24;
        int red1 = (value1 & 0x00FF0000) >> 16;
        int green1 = (value1 & 0x0000FF00) >> 8;
        int blue1 = (value1 & 0x000000FF);

        int alpha2 = (value2 & 0xFF000000) >>> 24;
        int red2 = (value2 & 0x00FF0000) >> 16;
        int green2 = (value2 & 0x0000FF00) >> 8;
        int blue2 = (value2 & 0x000000FF);

        int rgb = ((int) (alpha1 * (1.0 - distance) + alpha2 * distance) << 24) | ((int) (red1 * (1.0 - distance) + red2 * distance) << 16)
                | ((int) (green1 * (1.0 - distance) + green2 * distance) << 8) | (int) (blue1 * (1.0 - distance) + blue2 * distance);

        return rgb;
    }

    /**
     * Determines if the image has transparent pixels.
     *
     * @param image
     *            The image to check for transparent pixel.s
     * @return <code>true</code> of <code>false</code>, according to the result
     */
    public static boolean hasAlpha(Image image) {
        try {
            PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
            pg.grabPixels();

            return pg.getColorModel().hasAlpha();
        } catch (InterruptedException e) {
            return false;
        }
    }
}
