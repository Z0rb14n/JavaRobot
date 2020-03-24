/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jon;

import java.io.File;
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import static java.awt.event.MouseEvent.BUTTON1;
import static java.awt.event.MouseEvent.BUTTON2;
import static java.awt.event.MouseEvent.BUTTON3;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.util.HashMap;

/**
 *
 * @author adminasaurus
 */
// Extends Robot Class
public class RobotWrapper extends Robot {
    private static boolean isNonMac;
    final static int NORMALDELAY = 20;
    public static final String JPG = "jpg";
    public static final String JPEG = "jpeg";
    public static final String PNG = "png";
    public static final String BMP = "bmp";
    public static final String WBMP = "wbmp";
    public static final String GIF = "gif";
    private static final String ALLCAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ~%(){}|<>?";
    private static final String CAPS_CORRESPONDING = "abcdefghijklmnopqrstuvwxyz`590[]\\,./";
    private static final String ALLALTS = "º¡™£¢∞§¶•ªå∫ç∂ƒ©˙ˆ∆˚¬µ˜øπœ®ß†¨√∑≈¥Ω–";
    private static final String ALTS_CORRESPONDING = "0123456789abcdfghijklmnopqrstuvwxyz-";
    private static final String ALLSAlts = "ÅıÇÎ´Ï˝ÓˆÔÒÂ˜Ø∏Œ‰Íˇ¨◊„˛Á¸‚⁄€‹›ﬁﬂ‡°·—±”’»ÚÆ¯˘¿";
    private static final String SAlts_CORRESPONDING = "abcdefghijklmnopqrstuvwxyz0123456789-=[]\\;\',./";
    private static final HashMap<Character, Integer> Keys = new HashMap<>();
    private static final HashMap<Character, Character> Caps = new HashMap<>();
    private static final HashMap<Character, Character> Alts = new HashMap<>();
    private static final HashMap<Character, Character> SAlt = new HashMap<>();
    public final static String DESKTOP = "/Users/adminasaurus/Desktop/";
    public final static int DISPLAYHEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    public final static int DISPLAYWIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;

    // Constructor. Yeah. About that..
    public RobotWrapper() throws AWTException {
        super();
        initLettersNonMac();
    }
    
    // Constructor, but a boolean if you use a delay
    public RobotWrapper(boolean doDelay) throws AWTException {
        super();
        initLettersNonMac();
        if (doDelay) setAutoDelay(NORMALDELAY);
    }
    
    // Constructor, but a boolean if you use a delay
    public RobotWrapper(boolean doDelay, int delayAmount) throws AWTException {
        super();
        initLettersNonMac();
        if (doDelay) setAutoDelay(delayAmount);
    }

    //<editor-fold desc="Convenience Code">
    // MODIFIES: this
    // EFFECTS: initializes the keys/caps/Alts/Salts for Non-Mac operating Systems
    private static void initLettersNonMac() {
        isNonMac = true;
        initUniversal();
        // INIT WINDOWS KEY - I CANNOT TEST SINCE I'M A MAC USER
        System.gc();
    }

    // MODIFIES: this
    // EFFECTS: initializes the Keys/caps/ALts/Salts
    private static void initLetters() {
        isNonMac = false;
        initUniversal();
        Keys.put((char) 8984, KeyEvent.VK_META); //can use ⌘, mac command key, int code 8984
        assert (ALLALTS.length() == ALTS_CORRESPONDING.length());
        //WARNING: I,N,U NEEDS TO BE PRESSED TWICE
        for (int i = 0; i < ALLALTS.length(); i++) {
            Alts.put(ALLALTS.charAt(i), ALTS_CORRESPONDING.charAt(i));
        }
        assert (ALLSAlts.length() == SAlts_CORRESPONDING.length());
        for (int i = 0; i < ALLSAlts.length(); i++) {
            SAlt.put(ALLSAlts.charAt(i), SAlts_CORRESPONDING.charAt(i));
        }
        System.gc();
    }

    // MODIFIES: this
    // EFFECTS: initializes the keys/caps only
    private static void initUniversal() {
        for (char i = 'a'; i < 'z' + 1; i++) {
            Keys.put(i, i - 'a' + KeyEvent.VK_A);
        }
        for (char i = '0'; i < '9' + 1; i++) {
            Keys.put(i, i - '0' + KeyEvent.VK_0);
        }
        //essentially, shift the char int values to that of the key event VK values.
        Keys.put('`', KeyEvent.VK_BACK_QUOTE);
        Keys.put('-', KeyEvent.VK_MINUS);
        Keys.put('=', KeyEvent.VK_EQUALS);
        Keys.put('!', KeyEvent.VK_EXCLAMATION_MARK);
        Keys.put('@', KeyEvent.VK_AT);
        Keys.put('#', KeyEvent.VK_NUMBER_SIGN);
        Keys.put('$', KeyEvent.VK_DOLLAR);
        Keys.put('^', KeyEvent.VK_CIRCUMFLEX);
        Keys.put('&', KeyEvent.VK_AMPERSAND);
        Keys.put('*', KeyEvent.VK_ASTERISK);
        Keys.put('_', KeyEvent.VK_UNDERSCORE);
        Keys.put('+', KeyEvent.VK_PLUS);
        Keys.put('\t', KeyEvent.VK_TAB);
        Keys.put('\n', KeyEvent.VK_ENTER);
        Keys.put('[', KeyEvent.VK_OPEN_BRACKET);
        Keys.put(']', KeyEvent.VK_CLOSE_BRACKET);
        Keys.put('\\', KeyEvent.VK_BACK_SLASH);
        Keys.put(':', KeyEvent.VK_COLON);
        Keys.put(';', KeyEvent.VK_SEMICOLON);
        Keys.put(',', KeyEvent.VK_COLON);
        Keys.put('\'', KeyEvent.VK_QUOTE);
        Keys.put('"', KeyEvent.VK_QUOTEDBL);
        Keys.put(',', KeyEvent.VK_COMMA);
        Keys.put('.', KeyEvent.VK_PERIOD);
        Keys.put('/', KeyEvent.VK_SLASH);
        Keys.put(' ', KeyEvent.VK_SPACE);
        assert (ALLCAPS.length() == CAPS_CORRESPONDING.length());
        for (int i = 0; i < ALLCAPS.length(); i++) {
            Caps.put(ALLCAPS.charAt(i), CAPS_CORRESPONDING.charAt(i));
        }
    }

    /**
     * Clicks the mouse at specified position
     *
     * @param x x position to click at
     * @param y y position to click at
     */
    public void clickAt(int x, int y) {
        Point lol = MouseInfo.getPointerInfo().getLocation();
        int lolx = (int) lol.getX();
        int loly = (int) lol.getY();
        mouseMove(x, y);
        button1();
        mouseMove(lolx, loly);
    }

    /**
     * Clicks the mouse at specified position
     *
     * @param x x position to click at
     * @param y y position to click at
     * @param mouseEvent mouse type
     */
    public void clickAt(int x, int y, MouseEvent mouseEvent) {
        if (!mouseEvent.equals(BUTTON1) && !mouseEvent.equals(BUTTON2) && !mouseEvent.equals(BUTTON3)) {
            throw new IllegalArgumentException("clickAt called with invalid MouseEvent " + mouseEvent);
        }
        Point lol = MouseInfo.getPointerInfo().getLocation();
        int lolx = (int) lol.getX();
        int loly = (int) lol.getY();
        mouseMove(x, y);
        if (mouseEvent.equals(BUTTON1)) {
            button1();
        } else if (mouseEvent.equals(BUTTON2)) {
            button2();
        } else if (mouseEvent.equals(BUTTON3)) {
            button3();
        }
        mouseMove(lolx, loly);
    }

    /**
     * Convenience: presses Mouse1 and releases it
     */
    public void button1() {
        mousePress(InputEvent.BUTTON1_MASK);
        mouseRelease(InputEvent.BUTTON1_MASK);
    }

    /**
     * Convenience: presses Mouse2 and releases it
     */
    public void button2() {
        mousePress(InputEvent.BUTTON2_MASK);
        mouseRelease(InputEvent.BUTTON2_MASK);
    }

    /**
     * Convenience: presses Mouse3 and releases it
     */
    public void button3() {
        mousePress(InputEvent.BUTTON3_MASK);
        mouseRelease(InputEvent.BUTTON3_MASK);
    }

    /**
     * Runs keyPress but holds shift before running
     *
     * @param a_key keycode of key to press
     */
    public void keyPressCapitalized(int a_key) {
        keyPress(KeyEvent.VK_SHIFT);
        keyPressRelease(a_key);
        keyRelease(KeyEvent.VK_SHIFT);
    }

    /**
     * Presses and releases a key
     *
     * @param a_key key code
     */
    public void keyPressRelease(int a_key) {
        keyPress(a_key);
        keyRelease(a_key);
    }

    /**
     * Holds down keycode Hold and then types all keycodes types
     *
     * @param hold keycode of key to hold down
     * @param types keycodes of keys to press
     */
    public void holdType(int hold, int... types) {
        keyPress(hold);
        for (int type : types) {
            keyPressRelease(type);
        }
        keyRelease(hold);
    }

    /**
     * Holds down all keys in hold and then types the string
     *
     * @param hold list of keys to hold down
     * @param types String to enter
     */
    public void holdType(int[] hold, String types) {
        for (int lol : hold) {
            keyPress(lol);
        }
        for (int i = 0; i < types.length(); i++) {
            simplerType(types.charAt(i));
        }
        for (int lol : hold) {
            keyRelease(lol);
        }
    }

    /**
     * Holds down all keys in hold, and then types the integer keycodes
     *
     * @param hold list of integer keycodes to hold down
     * @param types integer keycodes to type
     */
    public void holdType(int[] hold, int... types) {
        for (int lol : hold) {
            keyPress(lol);
        }
        for (int type : types) {
            keyPressRelease(type);
        }
        for (int lol : hold) {
            keyRelease(lol);
        }
    }

    /**
     * Holds down all keys in hold and then types the characters
     *
     * @param hold list of integer keycodes to hold down
     * @param types list of characters to type
     */
    public void holdType(int[] hold, char... types) {
        for (int lol : hold) {
            keyPress(lol);
        }
        for (char type : types) {
            simplerType(type);
        }
        for (int lol : hold) {
            keyRelease(lol);
        }
    }

    /**
     * Holds down keycode hold and then types all characters
     *
     * @param hold integer keycode to hold down
     * @param types characters to type afterwards
     */
    public void holdType(int hold, char... types) {
        keyPress(hold);
        for (char type : types) {
            simplerType(type);
        }
        keyRelease(hold);
    }

    /**
     * Holds down keycode hold and then types the string
     *
     * @param hold integer keycode to hold
     * @param list String to type
     */
    public void holdType(int hold, String list) {
        if (list == null || list.length() == 0) {
            return;
        }
        keyPress(hold);
        for (int i = 0; i < list.length(); i++) {
            simplerType(list.charAt(i));
        }
        keyRelease(hold);
    }

    /**
     * Types a character by comparing it to the existing HashMaps
     *
     * @param character character to type
     * @throws IllegalArgumentException upon entering a character that cannot be
     * typed
     */
    public void simplerType(char character) {
        if (Keys.containsKey(character)) {
            keyPressRelease(Keys.get(character));
        } else if (Caps.containsKey(character)) {
            keyPressCapitalized(Keys.get(Caps.get(character)));
        } else if (!isNonMac) {
            if (Alts.containsKey(character)) {
                if (character == 'ˆ' || character == '˜' || character == '¨') {
                    holdType(KeyEvent.VK_ALT, Alts.get(character), Alts.get(character)); //alt-i,alt-n,alt-u needs double press
                } else {
                    holdType(KeyEvent.VK_ALT, Alts.get(character));
                }
            } else if (SAlt.containsKey(character)) {
                holdType(new int[]{KeyEvent.VK_ALT, KeyEvent.VK_SHIFT}, character);
            } else {
                throw new IllegalArgumentException("Cannot type character " + character);
            }
        }
    }

    /**
     * Runs simplerType() on every character in the string
     *
     * @param a
     */
    public void simplerType(String a) {
        if (a == null || a.length() == 0) {
            return;
        }
        for (int i = 0; i < a.length(); i++) {
            simplerType(a.charAt(i));
        }
    }
    
    /**
     * Returns mouse position lol
     * @return 
     */
    public static final Point getMousePosition() {
        return MouseInfo.getPointerInfo().getLocation();
    }
    //</editor-fold>

    //<editor-fold desc="Screenshot Code">
    /**
     * Takes a screenshot with the given format specified by the path Format is
     * one of "jpeg","jpg","png","bmp","wbmp","gif" Saves screenshot to the
     * given path Screenshot is of the whole screen
     *
     * @param path Path to save screenshot to
     */
    public void screenShot(String path) {
        if (path == null || path.length() < 5) {
            throw new IllegalArgumentException("BRUH WHAT");
        }
        String ext4 = path.substring(path.length() - 4, path.length());
        String ext3 = path.substring(path.length() - 3, path.length());
        String mode = null;
        if (ext4.compareToIgnoreCase(JPEG) == 0) {
            mode = JPEG;
        } else if (ext3.compareToIgnoreCase(JPG) == 0) {
            mode = JPG;
        } else if (ext3.compareToIgnoreCase(PNG) == 0) {
            mode = PNG;
        } else if (ext3.compareToIgnoreCase(BMP) == 0) {
            mode = BMP;
        } else if (ext4.compareToIgnoreCase(WBMP) == 0) {
            mode = WBMP;
        } else if (ext3.compareToIgnoreCase(GIF) == 0) {
            mode = GIF;
        } else {
            throw new IllegalArgumentException("BRUH WHAT");
        }
        File output = new File(path);
        try {
            ImageIO.write(createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())), mode, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes a screenshot with the given format specified by the path Format is
     * one of "jpeg","jpg","png","bmp","wbmp","gif" The screenshot is of the
     * specific area specified by the rectangle Saves to that path
     *
     * @param area Section of screen to take a screenshot of
     * @param path Path to save screenshot to
     */
    public void screenShot(Rectangle area, String path) {
        if (path == null || path.length() < 5) {
            throw new IllegalArgumentException("BRUH WHAT");
        }
        String ext4 = path.substring(path.length() - 4, path.length());
        String ext3 = path.substring(path.length() - 3, path.length());
        String mode = null;
        if (ext4.compareToIgnoreCase(JPEG) == 0) {
            mode = JPEG;
        } else if (ext3.compareToIgnoreCase(JPG) == 0) {
            mode = JPG;
        } else if (ext3.compareToIgnoreCase(PNG) == 0) {
            mode = PNG;
        } else if (ext3.compareToIgnoreCase(BMP) == 0) {
            mode = BMP;
        } else if (ext4.compareToIgnoreCase(WBMP) == 0) {
            mode = WBMP;
        } else if (ext3.compareToIgnoreCase(GIF) == 0) {
            mode = GIF;
        } else {
            throw new IllegalArgumentException("BRUH WHAT");
        }
        File output = new File(path);
        try {
            ImageIO.write(createScreenCapture(area), mode, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes a screenshot with the given format specified by the path Format is
     * one of "jpeg","jpg","png","bmp","wbmp","gif" Saves screenshot to the
     * given path Screenshot is of the screen specified by x/y/height/height
     * coords
     *
     * @param x top left horizontal coordinate of screenshot - starts at 0?
     * @param y top left vertical coordinate of screenshot - starts at 0?
     * @param width width of screenshot
     * @param height height of screenshot
     * @param path path to save screenshot to
     */
    public void screenShot(int x, int y, int width, int height, String path) {
        if (path == null || path.length() < 5) {
            throw new IllegalArgumentException("BRUH WHAT");
        }
        String ext4 = path.substring(path.length() - 4, path.length());
        String ext3 = path.substring(path.length() - 3, path.length());
        String mode = null;
        if (ext4.compareToIgnoreCase(JPEG) == 0) {
            mode = JPEG;
        } else if (ext3.compareToIgnoreCase(JPG) == 0) {
            mode = JPG;
        } else if (ext3.compareToIgnoreCase(PNG) == 0) {
            mode = PNG;
        } else if (ext3.compareToIgnoreCase(BMP) == 0) {
            mode = BMP;
        } else if (ext4.compareToIgnoreCase(WBMP) == 0) {
            mode = WBMP;
        } else if (ext3.compareToIgnoreCase(GIF) == 0) {
            mode = GIF;
        } else {
            throw new IllegalArgumentException("BRUH WHAT");
        }
        File output = new File(path);
        try {
            ImageIO.write(createScreenCapture(new Rectangle(x, y, width, height)), mode, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes a screenshot with the given format specified by the path Format is
     * one of "jpeg","jpg","png","bmp","wbmp","gif" Saves screenshot to the
     * given path Screenshot is of the screen specified by x/y/height/height
     * coords
     *
     * @param x top left horizontal coordinate of screenshot - starts at 0?
     * @param y top left vertical coordinate of screenshot - starts at 0?
     * @param width width of screenshot
     * @param height height of screenshot
     * @param path_file File object containing path to save screenshot to
     */
    public void screenShot(int x, int y, int width, int height, File path_file) {
        String path = path_file.getAbsolutePath();
        String ext4 = path.substring(path.length() - 4, path.length());
        String ext3 = path.substring(path.length() - 3, path.length());
        String mode = null;
        if (ext4.compareToIgnoreCase(JPEG) == 0) {
            mode = JPEG;
        } else if (ext3.compareToIgnoreCase(JPG) == 0) {
            mode = JPG;
        } else if (ext3.compareToIgnoreCase(PNG) == 0) {
            mode = PNG;
        } else if (ext3.compareToIgnoreCase(BMP) == 0) {
            mode = BMP;
        } else if (ext4.compareToIgnoreCase(WBMP) == 0) {
            mode = WBMP;
        } else if (ext3.compareToIgnoreCase(GIF) == 0) {
            mode = GIF;
        } else {
            throw new IllegalArgumentException("BRUH WHAT");
        }
        try {
            ImageIO.write(createScreenCapture(new Rectangle(x, y, width, height)), mode, path_file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>
}
