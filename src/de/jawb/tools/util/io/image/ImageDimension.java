package de.jawb.tools.util.io.image;

/**
 * @author dit (18.05.2013)
 */
public class ImageDimension {
    
    private final int width;
    private final int height;
                      
    /**
     * @param heigth
     * @param width
     */
    public ImageDimension(int width, int height) {
        super();
        this.height = height;
        this.width = width;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public boolean hasMinDimension(ImageDimension otherDim) {
        return this.width >= otherDim.width && this.height >= otherDim.height;
    }
    
    public String toShortString() {
        return width + "x" + height;
    }
    
    @Override
    public String toString() {
        return "ImageDimension [width=" + width + ", height=" + height + "]";
    }
    
    public static void main(String[] args) {
        
        System.out.println(new ImageDimension(150, 150).hasMinDimension(new ImageDimension(150, 150)));
        
    }
    
}
