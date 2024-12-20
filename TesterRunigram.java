import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TesterRunigram {

    public static final double EPS = 0.00001;

    public static void main(String[] args) {
        String testName = args[0];

        if (testName.equals("read")) {
            // Testing Runigram.read
            System.out.println("Testing Runigram.read...");
            if (testRead("tinypic.ppm")) {
                System.out.println("PASSED");
            } else {
                System.out.println("FAILED");
            }
        }
        
        if (testName.equals("horizontal")) {
            // Testing Runigram.flippedHorizontally
            System.out.println("Testing Runigram.flippedHorizontally...");
            if (testFlipHorizontally()) {
                System.out.println("PASSED");
            } else {
                System.out.println("FAILED");
            }
        }

        if (testName.equals("vertical")) {
            // Testing Runigram.flippedVertically
            System.out.println("Testing Runigram.flippedVertically...");
            if (testFlipHorizontally()) {
                System.out.println("PASSED");
            } else {
                System.out.println("FAILED");
            }
        }

        if (testName.equals("grayscaled")) {
            // Testing Runigram.grayScaled
            System.out.println("Testing Runigram.grayScaled...");
            if (testGrayScaled()) {
                System.out.println("PASSED");
            } else {
                System.out.println("FAILED");
            }
        }

        if (testName.equals("scaled")) {
            // Testing Runigram.scaled
            System.out.println("Testing Runigram.scaled...");
            if (testScaled()) {
                System.out.println("PASSED");
            } else {
                System.out.println("FAILED");
            }
        }

        if (testName.equals("blend")) {
            // Testing Runigram.blend
            System.out.println("Testing Runigram.blend...");
            if (testBlend()) {
                System.out.println("PASSED");
            } else {
                System.out.println("FAILED");
            }
        }


        // Color[][] img = Runigram.scaled(safeRead("eyes.ppm"), 241, 209);
        // Color[][] img = Runigram.blend(safeRead("escher.ppm"), safeRead("eyes.ppm"), 0.21);
        // int a = 2;
        // savePPM("blend_021.ppm", img);
        
    }

    /**
     * A tested function for the Runigram.read function.
     */
    public static boolean testRead(String imgPath) {
        Color[][] imgTiny = safeRead("tinypic.ppm");
        
        if (imgTiny == null) {
            return false;
        }

        boolean pixelsEqual = true;
        // First Row
        pixelsEqual = pixelsEqual && compareColors(imgTiny[0][0], Color.BLACK);
        pixelsEqual = pixelsEqual && compareColors(imgTiny[0][1], new Color(100, 0, 0));
        pixelsEqual = pixelsEqual && compareColors(imgTiny[0][2], Color.BLACK);
        pixelsEqual = pixelsEqual && compareColors(imgTiny[0][3], Color.MAGENTA);

        // Second Row
        pixelsEqual = pixelsEqual && compareColors(imgTiny[1][0], Color.BLACK);
        pixelsEqual = pixelsEqual && compareColors(imgTiny[1][1], new Color(0, 255, 175));
        pixelsEqual = pixelsEqual && compareColors(imgTiny[1][2], Color.BLACK);
        pixelsEqual = pixelsEqual && compareColors(imgTiny[1][3], Color.BLACK);

        // Third Row
        pixelsEqual = pixelsEqual && compareColors(imgTiny[2][0], Color.BLACK);
        pixelsEqual = pixelsEqual && compareColors(imgTiny[2][1], Color.BLACK);
        pixelsEqual = pixelsEqual && compareColors(imgTiny[2][2], new Color(0, 15, 175));
        pixelsEqual = pixelsEqual && compareColors(imgTiny[2][3], Color.BLACK);

        // Fourth Row
        pixelsEqual = pixelsEqual && compareColors(imgTiny[3][0], Color.MAGENTA);
        pixelsEqual = pixelsEqual && compareColors(imgTiny[3][1], Color.BLACK);
        pixelsEqual = pixelsEqual && compareColors(imgTiny[3][2], Color.BLACK);
        pixelsEqual = pixelsEqual && compareColors(imgTiny[3][3], Color.WHITE);

        return pixelsEqual;
    }

    /**
     * A tested function for the Runigram.FlipHorizontally function.
     */
    public static boolean testFlipHorizontally() {
        Color[][] flipped = Runigram.flippedHorizontally(safeRead("thor.ppm"));
        Color[][] res = safeRead("expected_results/horizontal.ppm");
        
        if (flipped == null || res == null) {
            return false;
        }
        return compareImages(flipped, res);
    }

    
    /**
     * A tested function for the Runigram.FlipVertically function.
     */
    public static boolean testFlipVertically() {
        Color[][] flipped = Runigram.flippedVertically(safeRead("thor.ppm"));
        Color[][] res = safeRead("expected_results/vertical.ppm");
        
        if (flipped == null || res == null) {
            return false;
        }
        return compareImages(flipped, res);
    }

    /**
     * A tested function for the Runigram.grayScaled function.
     */
    public static boolean testGrayScaled() {
        Color[][] flipped = Runigram.grayScaled(safeRead("xmen.ppm"));
        Color[][] res = safeRead("expected_results/xmen_gray.ppm");
        
        if (flipped == null || res == null) {
            return false;
        }

        boolean res1 = compareImages(flipped, res);

        flipped = Runigram.grayScaled(safeRead("cake.ppm"));
        res = safeRead("expected_results/cake_gray.ppm");
        
        if (flipped == null || res == null) {
            return false;
        }
        return res1 && compareImages(flipped, res);
    }

    /**
     * A tested function for the Runigram.scaled function.
     */
    public static boolean testScaled() {
        Color[][] flipped = Runigram.scaled(safeRead("ironman.ppm"), 100, 150);
        Color[][] res = safeRead("expected_results/ironman_100_150.ppm");
        
        if (flipped == null || res == null) {
            return false;
        }

        boolean res1 = compareImages(flipped, res);

        flipped = Runigram.scaled(safeRead("ironman.ppm"), 600, 400);
        res = safeRead("expected_results/ironman_600_400.ppm");
        
        if (flipped == null || res == null) {
            return false;
        }
        return res1 && compareImages(flipped, res);
    }

    /**
     * A tested function for the Runigram.blend function.
     */
    public static boolean testBlend() {
        Color[][] img1 = safeRead("escher.ppm");
        Color[][] img2 = safeRead("eyes.ppm");

        Color[][] blended = Runigram.blend(img1, img2, 0);
        Color[][] res = img2;
        
        if (blended == null || res == null) {
            return false;
        }
        boolean res1 = compareImages(blended, res);

        blended = Runigram.blend(img1, img2, 0.21);
        res = safeRead("expected_results/blend_021.ppm");
        
        if (blended == null || res == null) {
            return false;
        }
        boolean res2 = compareImages(blended, res);


        blended = Runigram.blend(img1, img2, 0.5);
        res = safeRead("expected_results/blend_05.ppm");
        
        if (blended == null || res == null) {
            return false;
        }
        boolean res3 = compareImages(blended, res);

        blended = Runigram.blend(img1, img2, 0.75);
        res = safeRead("expected_results/blend_075.ppm");
        
        if (blended == null || res == null) {
            return false;
        }
        boolean res4 = compareImages(blended, res);

        blended = Runigram.blend(img1, img2, 1);
        res = img1;
        
        if (blended == null || res == null) {
            return false;
        }
        boolean res5 = compareImages(blended, res);
        
        return res1 && res2 && res3 && res4 && res5;
    }


    // ========================================== Helper functions ===========================================

    public static Color[][] safeRead(String imgPath) {
        try {
            Color[][] img = Runigram.read(imgPath);
            return img;

        } catch (Exception e) {
            System.out.println("Failed to read <" + imgPath + "> using Runigram.read.");
            System.out.println("Please fix this before proceeding to the rest of the homework.");
            return null;
        }
    }

    public static boolean compareColors(Color c1, Color c2) {
        return c1.getRed() == c2.getRed() &&
               c1.getGreen() == c2.getGreen() &&
               c1.getBlue() == c2.getBlue();
    }

    public static boolean compareImages(Color[][] img1, Color[][] img2) {
        if (img1.length != img2.length || img1[0].length != img2[0].length) {
            return false;
        }

        for (int i = 0; i < img1.length; i++) {
            for (int j = 0; j < img1[0].length; j++) {
                if (!compareColors(img1[i][j], img2[i][j])) {
                    return false;
                }
            }
        }
        return true;
    } 

    public static void savePPM(String filename, Color[][] pixels)  {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Write the PPM header
            writer.write("P3\n");
            writer.write(pixels[0].length + " " + pixels.length + "\n");
            writer.write("255\n");

            // Write pixel data
            for (int y = 0; y < pixels.length; y++) {
                for (int x = 0; x < pixels[0].length; x++) {
                    int[] rgb = {pixels[y][x].getRed(), pixels[y][x].getGreen(), pixels[y][x].getBlue()};
                    writer.write(rgb[0] + " " + rgb[1] + " " + rgb[2] + " ");
                }
                writer.newLine(); // Newline at the end of each row
            }

        } catch (IOException e) {
            System.out.println("Failed");
        }
    }

    
}





